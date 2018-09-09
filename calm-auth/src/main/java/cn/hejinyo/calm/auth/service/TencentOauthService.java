package cn.hejinyo.calm.auth.service;

import cn.hejinyo.calm.auth.model.dto.TencentUserDTO;

/**
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/8/25 22:49
 */
public interface TencentOauthService {

    String loginUrl();

    TencentUserDTO loginRedirect(String code, String state);
}
