package cn.hejinyo.calm.auth.feign;

import cn.hejinyo.calm.auth.feign.fallback.UserServiceFallbackImpl;
import cn.hejinyo.calm.common.model.dto.SysUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 登陆逻辑查询用户信息
 *
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/8/29 21:55
 */
@FeignClient(name = "calm-jelly", fallback = UserServiceFallbackImpl.class)
public interface UserService {

    /**
     * 通过用户名查询用户、角色信息
     *
     * @param userName 用户名
     * @return SysUserDTO
     */
    @GetMapping("/user/findByUserName/{userName}")
    SysUserDTO findUserByUserName(@PathVariable("userName") String userName);

}
