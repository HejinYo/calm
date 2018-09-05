package cn.hejinyo.calm.auth.feign.fallback;

import cn.hejinyo.calm.auth.feign.JellyApiService;
import cn.hejinyo.calm.common.basis.model.dto.SysUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/8/29 21:56
 */
@Service
@Slf4j
public class JellyApiServiceFallbackImpl implements JellyApiService {

    @Override
    public SysUserDTO findUserByUserName(String username) {
        log.error("调用{}异常:{}", "findUserByUserName", username);
        return null;
    }

    /**
     * 查找用户编号对应的角色编码字符串
     */
    @Override
    public Set<String> getUserRoleSet(int userId) {
        log.error("调用 {} 异常:{}", "查找用户编号对应的角色编码字符串", userId);
        return new HashSet<>();
    }

    /**
     * 查找用户编号对应的权限编码字符串
     */
    @Override
    public Set<String> getUserPermSet(int userId) {
        log.error("调用 {} 异常:{}", "查找用户编号对应的权限编码字符串", userId);
        return new HashSet<>();
    }

}
