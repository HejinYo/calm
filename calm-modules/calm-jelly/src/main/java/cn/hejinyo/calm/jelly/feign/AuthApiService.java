package cn.hejinyo.calm.jelly.feign;

import cn.hejinyo.calm.common.basis.consts.Constant;
import cn.hejinyo.calm.common.web.shiro.AuthCheckResult;
import cn.hejinyo.calm.jelly.feign.fallback.AuthApiServiceFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 登陆逻辑查询用户信息
 *
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/8/29 21:55
 */
@FeignClient(name = "calm-auth", fallback = AuthApiServiceFallbackImpl.class)
public interface AuthApiService {

    /**
     * 通过用户名查询用户、角色信息
     */
    @GetMapping(value = Constant.SERVER_API + "/jelly/check/{userId}/{jti}")
    AuthCheckResult checkToken(@PathVariable("userId") Integer userId, @PathVariable("jti") String jti);
}
