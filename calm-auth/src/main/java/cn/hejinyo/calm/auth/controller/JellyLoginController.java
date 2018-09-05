package cn.hejinyo.calm.auth.controller;

import cn.hejinyo.calm.auth.service.JellyService;
import cn.hejinyo.calm.common.basis.model.dto.SysUserDTO;
import cn.hejinyo.calm.common.basis.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * jelly登录控制器
 *
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/9/1 22:47
 */
@RestController
@RequestMapping("/jelly")
public class JellyLoginController {

    @Autowired
    private JellyService jellyService;

    /**
     * 执行登录,返回userToken
     */
    @PostMapping(value = "/login")
    public Result login(@RequestBody SysUserDTO loginUser) {
        return Result.result(jellyService.doLogin(jellyService.checkUser(loginUser)));
    }

}
