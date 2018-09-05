package cn.hejinyo.calm.auth.service.impl;

import cn.hejinyo.calm.auth.feign.JellyApiService;
import cn.hejinyo.calm.auth.service.JellyService;
import cn.hejinyo.calm.common.basis.consts.Constant;
import cn.hejinyo.calm.common.basis.consts.StatusCode;
import cn.hejinyo.calm.common.basis.model.dto.SysUserDTO;
import cn.hejinyo.calm.common.basis.utils.JsonUtil;
import cn.hejinyo.calm.common.basis.utils.Tools;
import cn.hejinyo.calm.common.redis.utils.RedisKeys;
import cn.hejinyo.calm.common.redis.utils.RedisUtils;
import cn.hejinyo.calm.common.web.exception.InfoException;
import cn.hejinyo.calm.common.web.shiro.AuthCheckResult;
import cn.hejinyo.calm.common.web.utils.ShiroUtils;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

/**
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/9/2 18:55
 */
@Service
public class JellyServiceImpl implements JellyService {

    @Autowired
    private JellyApiService jellyApiService;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 验证登录用户
     */
    @Override
    public SysUserDTO checkUser(SysUserDTO loginUser) {
        // 根据用户名查找用户，进行密码匹配
        SysUserDTO userDTO = jellyApiService.findUserByUserName(loginUser.getUserName());
        // 如果无相关用户或已删除则返回null
        if (null == userDTO) {
            throw new InfoException(StatusCode.LOGIN_USER_NOEXIST);
        } else if (Constant.Status.DISABLE.equals(userDTO.getState())) {
            throw new InfoException(StatusCode.LOGIN_USER_LOCK);
        }
        //验证密码
        if (!userDTO.getUserPwd().equals(ShiroUtils.userPassword(loginUser.getUserPwd(), userDTO.getUserSalt()))) {
            throw new InfoException(StatusCode.LOGIN_PASSWORD_ERROR);
        }
        return userDTO;
    }


    /**
     * 处理登录逻辑,创建token
     */
    @Override
    public String doLogin(SysUserDTO userDTO) {
        Integer userId = userDTO.getUserId();
        //创建jwt token
        String token = Tools.createToken("jelly", true, userId, userDTO.getUserName(), Constant.JWT_SIGN_KEY, Constant.JWT_EXPIRES_DEFAULT);
        String jti = Tools.tokenInfo(token, Constant.JWT_ID, String.class);
        redisUtils.hset(RedisKeys.storeUser(userId), RedisKeys.USER_TOKEN, jti);
        return token;
    }

    /**
     * 查找用户编号对应的角色编码字符串
     */
    @Override
    public Set<String> getUserRoleSet(int userId) {
        // redis中获得角色信息
        Set<String> roleSet = redisUtils.hget(RedisKeys.storeUser(userId), RedisKeys.USER_ROLE, new TypeToken<Set<String>>() {
        }.getType());

        // 不存在则数据库获得角色信息
        if (roleSet == null) {
            roleSet = jellyApiService.getUserRoleSet(userId);
        }
        if (roleSet != null) {
            roleSet.removeIf(Objects::isNull);
            redisUtils.hset(RedisKeys.storeUser(userId), RedisKeys.USER_ROLE, roleSet);
        }
        return roleSet;
    }

    /**
     * 查找用户编号对应的权限编码字符串
     */
    @Override
    public Set<String> getUserPermSet(int userId) {
        // redis中获得权限信息
        Set<String> permissionsSet = redisUtils.hget(RedisKeys.storeUser(userId), RedisKeys.USER_PERM, new TypeToken<Set<String>>() {
        }.getType());

        // 不存在则数据库获得权限信息
        if (permissionsSet == null) {
            permissionsSet = jellyApiService.getUserPermSet(userId);
        }
        if (permissionsSet != null) {
            permissionsSet.removeIf(Objects::isNull);
            redisUtils.hset(RedisKeys.storeUser(userId), RedisKeys.USER_PERM, permissionsSet);
        }
        return permissionsSet;
    }

    /**
     * 验证token 通过返回角色权限信息
     */
    @Override
    public AuthCheckResult checkToken(Integer userId, String jti) {
        String checkJti = redisUtils.hget(RedisKeys.storeUser(userId), RedisKeys.USER_TOKEN, String.class);
        AuthCheckResult result = new AuthCheckResult();
        if (jti.equals(checkJti)) {
            result.setPass(true);
            result.setRoleSet(getUserRoleSet(userId));
            result.setPermSet(getUserPermSet(userId));
        }
        return result;
    }
}
