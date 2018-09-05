package cn.hejinyo.calm.jelly.service.impl;

import cn.hejinyo.calm.common.basis.consts.Constant;
import cn.hejinyo.calm.jelly.dao.JellyLoginDao;
import cn.hejinyo.calm.jelly.model.SysUserEntity;
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
        Set<String> roleSet = roleSet = userId == Constant.SUPER_ADMIN ? jellyLoginDao.findAllRoleSet() : jellyLoginDao.findUserRoleSet(userId);
        roleSet.removeIf(Objects::isNull);
        return roleSet;
    }

    /**
     * 查找用户编号对应的权限编码字符串
     */
    @Override
    public Set<String> getUserPermSet(int userId) {
        // redis中获得权限信息
        Set<String> permissionsSet = permissionsSet = userId == Constant.SUPER_ADMIN ? jellyLoginDao.findAllPermSet() : jellyLoginDao.findUserPermSet(userId);
        permissionsSet.removeIf(Objects::isNull);
        return permissionsSet;
    }

}
