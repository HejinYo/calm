package cn.hejinyo.calm.common.basis.model.vo;

import cn.hejinyo.calm.common.basis.validator.RestfulValid;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 手机验证码登录
 *
 * @author : heshuangshuang
 * @date : 2018/7/28 16:17
 */
@Data
public class PhoneLoginVO {

    /**
     * 手机
     */
    @NotBlank(message = "手机不能为空", groups = {RestfulValid.POST.class})
    private String phone;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空", groups = {RestfulValid.POST.class})
    private String code;
}
