package cn.hejinyo.calm.jelly.dao;

import cn.hejinyo.calm.jelly.model.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/3/20 22:52
 */
@Mapper
public interface JellyLoginDao {

    /**
     * 执行登录，查询用户登录信息
     */
    SysUserEntity findByUserName(String userName);

    /**
     * 查找所有角色编码字符串，管理员使用
     */
    Set<String> findAllRoleSet();

    /**
     * 查找所有授权编码字符串，管理员使用
     */
    Set<String> findAllPermSet();

    /**
     * 查找用户编号对应的角色编码字符串
     */
    Set<String> findUserRoleSet(int userId);

    /**
     * 查找用户编号对应的权限编码字符串
     */
    Set<String> findUserPermSet(int userId);

    SysUserEntity findByPhone(String userName);

}
