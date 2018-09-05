package cn.hejinyo.calm.auth.feign;

import cn.hejinyo.calm.auth.feign.fallback.JellyApiServiceFallbackImpl;
import cn.hejinyo.calm.common.basis.consts.Constant;
import cn.hejinyo.calm.common.basis.model.dto.SysUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

/**
 * 登陆逻辑查询用户信息
 *
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/8/29 21:55
 */
@FeignClient(name = "calm-jelly", fallback = JellyApiServiceFallbackImpl.class)
public interface JellyApiService {

    /**
     * 通过用户名查询用户信息
     */
    @GetMapping(Constant.SERVER_API + "/login/findByUserName/{userName}")
    SysUserDTO findUserByUserName(@PathVariable("userName") String userName);

    /**
     * 查找用户编号对应的角色编码字符串
     */
    @GetMapping(value = Constant.SERVER_API + "/login/getUserRoleSet/{userId}")
    Set<String> getUserRoleSet(@PathVariable("userId") int userId);

    /**
     * 查找用户编号对应的权限编码字符串
     */
    @GetMapping(value = Constant.SERVER_API + "/login/getUserPermSet/{userId}")
    Set<String> getUserPermSet(@PathVariable("userId") int userId);
}
