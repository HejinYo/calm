package cn.hejinyo.calm.jelly.service;

import cn.hejinyo.calm.jelly.model.dto.LoginUserDTO;
import cn.hejinyo.calm.jelly.model.dto.PhoneLoginDTO;

/**
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/6/8 22:56
 */
public interface LoginService {

    /**
     * 发送电话登录验证码
     */
    boolean sendPhoneCode(String phone);

    /**
     * 手机用户登录
     */
    LoginUserDTO phoneLogin(PhoneLoginDTO phoneLogin);

}
