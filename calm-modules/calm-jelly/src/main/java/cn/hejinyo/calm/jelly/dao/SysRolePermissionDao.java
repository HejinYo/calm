package cn.hejinyo.calm.jelly.dao;

import cn.hejinyo.calm.common.basis.base.BaseDao;
import cn.hejinyo.calm.jelly.model.entity.SysRolePermissionEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * sys_role_permission 持久化层
 *
 * @author : HejinYo   hejinyo@gmail.com
 * @date : 2018/06/14 21:57
 */
@Mapper
public interface SysRolePermissionDao extends BaseDao<SysRolePermissionEntity, Integer> {
    /**
     * 先删除角色与权限关系
     */
    int deleteByRoleId(Integer roleId);

    /**
     * 删除多个角色与权限关系
     */
    int deleteByRoleIds(Integer[] roleIds);

    /**
     * 权限与角色关系
     */
    int deleteByPermId(Integer permId);
}