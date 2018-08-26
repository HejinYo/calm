package cn.hejinyo.calm.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/8/26 20:26
 */
@RestController
@RequestMapping
public class AuthenticationController {

    /**
     * 认证页面
     */
    @GetMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }
}
