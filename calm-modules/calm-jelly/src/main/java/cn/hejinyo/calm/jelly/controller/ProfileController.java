package cn.hejinyo.calm.jelly.controller;

import cn.hejinyo.calm.common.basis.model.vo.UserNameLoginVO;
import cn.hejinyo.calm.common.basis.utils.Result;
import cn.hejinyo.calm.common.web.utils.ShiroUtils;
import cn.hejinyo.calm.jelly.service.JellyLoginService;
import cn.hejinyo.calm.jelly.service.SysResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 系统用户登录控制器
 *
 * @author : HejinYo   hejinyo@gmail.com
 * @date : 2017/8/16 22:14
 */
@RestController
@RequestMapping("/")
@Slf4j
public class ProfileController {

    @Autowired
    private SysResourceService sysResourceService;

    @Autowired
    private JellyLoginService jellyLoginService;

    /**
     * 获得当前用户redis中的用户信息
     */
    @GetMapping("/userInfo")
    public Result getUserInfo() {
        return Result.ok(jellyLoginService.getUserInfo());
    }

    /**
     * 获得用户菜单
     */
    @GetMapping("/userMenu")
    public Result userMenu() {
        return Result.ok(sysResourceService.getUserMenuTree(ShiroUtils.getUserId()));
    }

    /**
     * 获得用户权限
     */
    @GetMapping("/userPerm")
    public Result userPerm() {
        return Result.ok(jellyLoginService.getUserPermSet(ShiroUtils.getUserId()));
    }

    /**
     * 获得用户角色
     */
    @GetMapping("/userRole")
    public Result userRole() {
        return Result.ok(jellyLoginService.getUserRoleSet(ShiroUtils.getUserId()));
    }

    /**
     * 解锁屏幕，验证密码
     */
    @PostMapping("/unlock")
    public Result lockScreen(@RequestBody UserNameLoginVO user) {
        if (jellyLoginService.unlock(user.getUserPwd())) {
            Result.ok();
        }
        return Result.error("密码错误");
    }
}
