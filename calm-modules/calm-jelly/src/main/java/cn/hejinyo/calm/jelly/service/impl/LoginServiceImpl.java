package cn.hejinyo.calm.jelly.service.impl;

import cn.hejinyo.calm.common.basis.consts.StatusCode;
import cn.hejinyo.calm.common.basis.utils.SmsUtils;
import cn.hejinyo.calm.common.basis.utils.StringUtils;
import cn.hejinyo.calm.common.redis.utils.RedisKeys;
import cn.hejinyo.calm.common.redis.utils.RedisUtils;
import cn.hejinyo.calm.common.web.exception.InfoException;
import cn.hejinyo.calm.jelly.model.SysUserEntity;
import cn.hejinyo.calm.jelly.model.dto.LoginUserDTO;
import cn.hejinyo.calm.jelly.model.dto.PhoneLoginDTO;
import cn.hejinyo.calm.jelly.service.LoginService;
import cn.hejinyo.calm.jelly.service.ShiroService;
import cn.hejinyo.calm.jelly.service.SysUserService;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 系统登录业务
 *
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/6/8 22:56
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private ShiroService shiroService;
    @Autowired
    private SysUserService sysUserService;

    /**
     * 发送电话登录验证码
     */
    @Override
    public boolean sendPhoneCode(String phone) {
        // 验证电话号码格式
        String regEx = "^$|^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(phone);
        if (!matcher.find()) {
            throw new InfoException("电话号码格式错误");
        }
        String code = String.valueOf(RandomUtils.nextInt(6));
        boolean result = SmsUtils.sendLogin(phone, code);
        if (result) {
            redisUtils.setEX(RedisKeys.smsSingle(phone), code, 300);
        }
        return result;
    }

    /**
     * 手机用户登录
     */
    @Override
    public LoginUserDTO phoneLogin(PhoneLoginDTO phoneLogin) {
        String phone = phoneLogin.getPhone();
        String code = phoneLogin.getCode();
        // 匹配验证码
        String localCode = redisUtils.get(RedisKeys.smsSingle(phone), String.class);
        if (StringUtils.isEmpty(localCode)) {
            throw new InfoException("验证码过期，请重新获取");
        }
        // 删除验证码
        redisUtils.delete(RedisKeys.smsSingle(phone));
        if (!code.equals(localCode)) {
            throw new InfoException("验证码错误");
        }
        // 根据号码查询用户
        LoginUserDTO userDTO = shiroService.getPhoneUser(phone);
        if (null == userDTO) {
            // 创建一个用户
            SysUserEntity sysUser = new SysUserEntity();
            sysUser.setPhone(phone);
            sysUser.setUserName(phone);
            sysUser.setUserPwd("123456");
            sysUser.setNickName("游客");
            sysUser.setAvatar("http://ow1prafcd.bkt.clouddn.com/hejinyo.jpg");
            sysUser.setRoleIdList(Collections.singletonList(1));
            sysUser.setDeptIdList(Collections.singletonList(1));
            sysUser.setState(0);
            sysUserService.save(sysUser);
            userDTO = shiroService.getPhoneUser(phone);
        }

        // 如果无相关用户或已删除则返回null
        if (null == userDTO) {
            throw new InfoException(StatusCode.LOGIN_USER_NOEXIST);
        } else if (1 == userDTO.getState()) {
            throw new InfoException(StatusCode.LOGIN_USER_LOCK);
        }
        return userDTO;
    }

}
