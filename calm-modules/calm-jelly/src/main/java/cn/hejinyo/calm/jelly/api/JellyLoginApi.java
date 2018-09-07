package cn.hejinyo.calm.jelly.api;

import cn.hejinyo.calm.common.basis.consts.Constant;
import cn.hejinyo.calm.common.basis.model.dto.SysUserDTO;
import cn.hejinyo.calm.common.basis.utils.PojoConvertUtil;
import cn.hejinyo.calm.jelly.service.JellyLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * 系统用户登录微服务接口
 *
 * @author : HejinYo   hejinyo@gmail.com
 * @date : 2017/8/16 22:14
 */
@RestController
@RequestMapping(Constant.SERVER_API + "/login")
@Slf4j
public class JellyLoginApi {

    @Autowired
    private JellyLoginService jellyLoginService;

    /**
     * 根据用户名查询用户信息
     */
    @GetMapping(value = "/findByUserName/{userName}")
    public SysUserDTO findByUserName(@PathVariable("userName") String userName) {
        return PojoConvertUtil.convert(jellyLoginService.findByUserName(userName), SysUserDTO.class);
    }

    /**
     * 查找用户编号对应的角色编码字符串
     */
    @GetMapping(value = "/getUserRoleSet/{userId}")
    public Set<String> getUserRoleSet(@PathVariable("userId") int userId) {
        return jellyLoginService.getUserRoleSet(userId);
    }

    /**
     * 查找用户编号对应的权限编码字符串
     */
    @GetMapping(value = "/getUserPermSet/{userId}")
    public Set<String> getUserPermSet(@PathVariable("userId") int userId) {
        return jellyLoginService.getUserPermSet(userId);
    }

    /**
     * 根据号码查询用户
     */
    @GetMapping(value = Constant.SERVER_API + "/login/findByPhone/{phone}")
    public SysUserDTO findByPhone(@PathVariable("phone") String phone) {
        return PojoConvertUtil.convert(jellyLoginService.findByPhone(phone), SysUserDTO.class);
    }
}
