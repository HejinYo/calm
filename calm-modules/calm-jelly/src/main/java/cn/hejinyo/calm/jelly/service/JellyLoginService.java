package cn.hejinyo.calm.jelly.service;

import cn.hejinyo.calm.jelly.model.entity.SysUserEntity;

import java.util.Set;

/**
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/3/20 22:48
 */
public interface JellyLoginService {
    /**
     * 获取登录用户信息
     */
    SysUserEntity getUserInfo();

    /**
     * 执行登录，查询用户登录信息
     */
    SysUserEntity findByUserName(String userName);

    /**
     * 查找用户编号对应的角色编码字符串
     */
    Set<String> getUserRoleSet(int userId);

    /**
     * 查找用户编号对应的权限编码字符串
     */
    Set<String> getUserPermSet(int userId);

    /**
     * 手机登录，查询用户登录信息
     */
    SysUserEntity findByPhone(String phone);

    /**
     * 解锁屏幕
     */
    boolean unlock(String userPwd);
}
