package cn.hejinyo.calm.common.basis.model.vo;

import cn.hejinyo.calm.common.basis.validator.RestfulValid;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author : heshuangshuang
 * @date : 2018/7/28 16:17
 */
@Data
public class UserNameLoginVO {

    /**
     * 用户名 user_name
     **/
    @NotBlank(message = "用户名不能为空", groups = {RestfulValid.POST.class})
    private String userName;

    /**
     * 用户密码 user_pwd
     **/
    @NotBlank(message = "密码不能为空", groups = {RestfulValid.POST.class})
    private String userPwd;
}
