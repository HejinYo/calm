package cn.hejinyo.calm.common.basis.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * sys_user 实体类
 *
 * @author : HejinYo   hejinyo@gmail.com
 * @date : 2018/06/08 23:20
 */
@Data
public class SysUserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号 user_id
     **/
    private Integer userId;

    /**
     * 用户昵称 nick_name
     **/
    private String nickName;

    /**
     * 用户名 user_name
     **/
    private String userName;

    /**
     * 用户密码 user_pwd
     **/
    @JSONField(serialize = false)
    private String userPwd;

    /**
     * 用户盐 user_salt
     **/
    @JSONField(serialize = false)
    private String userSalt;

    /**
     * 头像 avatar
     **/
    private String avatar;

    /**
     * 邮箱 email
     **/
    private String email;

    /**
     * 手机号 phone
     **/
    private String phone;

    /**
     * 最后登录IP login_ip
     **/
    private String loginIp;

    /**
     * 最后登录时间 login_time
     **/
    private Date loginTime;

    /**
     * 用户状态 0：正常；1：禁用； state
     **/
    @JSONField(serialize = false)
    private Integer state;

}