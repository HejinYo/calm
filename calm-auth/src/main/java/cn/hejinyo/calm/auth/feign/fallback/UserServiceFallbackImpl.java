package cn.hejinyo.calm.auth.feign.fallback;

import cn.hejinyo.calm.auth.feign.UserService;
import cn.hejinyo.calm.common.model.dto.SysUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/8/29 21:56
 */
@Service
@Slf4j
public class UserServiceFallbackImpl implements UserService {

    @Override
    public SysUserDTO findUserByUserName(String username) {
        log.error("调用{}异常:{}", "findUserByUserName", username);
        return null;
    }

}
