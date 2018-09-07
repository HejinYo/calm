package cn.hejinyo.calm.auth.controller;

import cn.hejinyo.calm.common.basis.model.vo.PhoneLoginVO;
import cn.hejinyo.calm.auth.service.JellyService;
import cn.hejinyo.calm.common.basis.annotation.SysLogger;
import cn.hejinyo.calm.common.basis.consts.Constant;
import cn.hejinyo.calm.common.basis.model.dto.SysUserDTO;
import cn.hejinyo.calm.common.basis.utils.Result;
import cn.hejinyo.calm.common.basis.utils.StringUtils;
import cn.hejinyo.calm.common.basis.validator.RestfulValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public Result login(@Validated(RestfulValid.POST.class) @RequestBody SysUserDTO loginUser) {
        return Result.result(jellyService.doLogin(jellyService.checkUser(loginUser)));
    }

    /**
     * 手机号,验证码登录,返回userToken
     */
    @PostMapping(value = "/login/phone")
    public Result phoneLogin(@Validated(RestfulValid.POST.class) @RequestBody PhoneLoginVO phoneLogin) {
        return Result.result(jellyService.doLogin(jellyService.phoneLogin(phoneLogin)));
    }

    /**
     * 登出
     */
    @PutMapping(value = "/logout")
    public Result logout(HttpServletRequest request) {
        String userToken = request.getHeader(Constant.AUTHOR_PARAM);
        if (StringUtils.isNotEmpty(userToken)) {
            jellyService.logout(userToken);
        }
        return Result.ok();
    }

    /**
     * 发送手机登录验证码,暂时不做相关限制
     */
    @PostMapping(value = "/login/code/{phone}")
    @SysLogger("发送验证码")
    public Result sendLoginCode(@PathVariable("phone") String phone) {
        if (jellyService.sendPhoneCode(phone)) {
            return Result.ok();
        }
        return Result.error("验证码发送失败");
    }

}
