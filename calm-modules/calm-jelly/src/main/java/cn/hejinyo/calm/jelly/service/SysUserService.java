package cn.hejinyo.calm.jelly.service;

import cn.hejinyo.calm.common.basis.base.BaseService;
import cn.hejinyo.calm.jelly.model.entity.SysUserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

/**
 * @author : HejinYo   hejinyo@gmail.com
 * @date : 2017/6/17 17:04
 */
public interface SysUserService extends BaseService<SysUserEntity, Integer> {
    /**
     * 更新用户登录信息
     */
    void updateUserLoginInfo(Integer userId);

    /**
     * 用户名是否存在
     */
    boolean isExistUserName(String userName);

    /**
     * 修改密码
     */
    int updatePassword(HashMap<String, Object> param);

    /**
     * 修改个人信息
     */
    int updateUserInfo(SysUserEntity sysUser);

    /**
     * 修改头像
     */
    String updateUserAvatar(MultipartFile file);
}
