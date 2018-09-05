package cn.hejinyo.calm.auth.api;

import cn.hejinyo.calm.auth.service.JellyService;
import cn.hejinyo.calm.common.basis.consts.Constant;
import cn.hejinyo.calm.common.web.shiro.AuthCheckResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * jelly登录控制器
 *
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/9/1 22:47
 */
@RestController
@RequestMapping(Constant.SERVER_API + "/jelly")
public class JellyAuthApi {

    @Autowired
    private JellyService jellyService;

    /**
     * 校验用户token，成功则返回角色，权限
     */
    @GetMapping(value = "/check/{userId}/{jti}")
    public AuthCheckResult checkToken(@PathVariable("userId") Integer userId, @PathVariable("jti") String jti) {
        return jellyService.checkToken(userId, jti);
    }

    /**
     * 查找用户编号对应的角色编码字符串
     */
    @PostMapping(value = "/getUserRoleSet/{userId}")
    public Set<String> getUserRoleSet(@PathVariable Integer userId) {
        return jellyService.getUserRoleSet(userId);
    }

    /**
     * 查找用户编号对应的权限编码字符串
     */
    @PostMapping(value = "/getUserPermSet/{userId}")
    public Set<String> getUserPermSet(@PathVariable Integer userId) {
        return jellyService.getUserPermSet(userId);
    }

}
