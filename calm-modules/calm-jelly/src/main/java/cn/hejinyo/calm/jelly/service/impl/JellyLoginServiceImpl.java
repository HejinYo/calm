package cn.hejinyo.calm.jelly.service.impl;

import cn.hejinyo.calm.common.basis.consts.Constant;
import cn.hejinyo.calm.common.web.utils.ShiroUtils;
import cn.hejinyo.calm.jelly.dao.JellyLoginDao;
import cn.hejinyo.calm.jelly.model.entity.SysUserEntity;
import cn.hejinyo.calm.jelly.service.JellyLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

/**
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/3/20 22:48
 */
@Service
public class JellyLoginServiceImpl implements JellyLoginService {
    @Autowired
    private JellyLoginDao jellyLoginDao;

    /**
     * 获取登录用户信息
     */
    @Override
    public SysUserEntity getUserInfo() {
        return jellyLoginDao.findByUserName(ShiroUtils.getUserName());
    }

    /**
     * 根据用户名查询用户信息
     */
    @Override
    public SysUserEntity findByUserName(String userName) {
        return jellyLoginDao.findByUserName(userName);
    }

    /**
     * 查找用户编号对应的角色编码字符串
     */
    @Override
    public Set<String> getUserRoleSet(int userId) {
        // redis中获得角色信息
        Set<String> roleSet = userId == Constant.SUPER_ADMIN ? jellyLoginDao.findAllRoleSet() : jellyLoginDao.findUserRoleSet(userId);
        roleSet.removeIf(Objects::isNull);
        return roleSet;
    }

    /**
     * 查找用户编号对应的权限编码字符串
     */
    @Override
    public Set<String> getUserPermSet(int userId) {
        // redis中获得权限信息
        Set<String> permissionsSet = userId == Constant.SUPER_ADMIN ? jellyLoginDao.findAllPermSet() : jellyLoginDao.findUserPermSet(userId);
        permissionsSet.removeIf(Objects::isNull);
        return permissionsSet;
    }

    /**
     * 手机登录，查询用户登录信息
     */
    @Override
    public SysUserEntity findByPhone(String phone) {
        return jellyLoginDao.findByPhone(phone);
    }

    /**
     * 解锁屏幕
     */
    @Override
    public boolean unlock(String userPwd) {
        SysUserEntity sysUserEntity = jellyLoginDao.findByUserName(ShiroUtils.getUserName());
        if (sysUserEntity != null) {
            return sysUserEntity.getUserPwd().equals(ShiroUtils.userPassword(userPwd, sysUserEntity.getUserSalt()));
        }
        return false;
    }
}
