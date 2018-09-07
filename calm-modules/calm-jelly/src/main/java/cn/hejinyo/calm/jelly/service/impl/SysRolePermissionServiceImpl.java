package cn.hejinyo.calm.jelly.service.impl;

import cn.hejinyo.calm.common.basis.base.BaseServiceImpl;
import cn.hejinyo.calm.jelly.dao.SysRolePermissionDao;
import cn.hejinyo.calm.jelly.model.entity.SysRolePermissionEntity;
import cn.hejinyo.calm.jelly.service.SysRolePermissionService;
import cn.hejinyo.calm.common.web.utils.ShiroUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色权限管理业务
 *
 * @author : HejinYo   hejinyo@gmail.com
 * @date : 2017/6/17 17:04
 */
@Service
public class SysRolePermissionServiceImpl extends BaseServiceImpl<SysRolePermissionDao, SysRolePermissionEntity, Integer> implements SysRolePermissionService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int save(Integer roleId, List<Integer> permIdList) {
        //先删除角色与权限关系
        int count = baseDao.deleteByRoleId(roleId);
        if (permIdList == null || permIdList.size() == 0) {
            return count;
        }
        //保存角色与权限关系
        List<SysRolePermissionEntity> rolePermissionList = new ArrayList<>();
        for (Integer permId : permIdList) {
            SysRolePermissionEntity rolePermission = new SysRolePermissionEntity();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermId(permId);
            rolePermission.setCreateId(ShiroUtils.getUserId());
            rolePermissionList.add(rolePermission);
        }

        return baseDao.saveBatch(rolePermissionList);
    }

    /**
     * 删除多个角色与权限关系
     */
    @Override
    public int deleteByRoleIds(Integer[] roleIds) {
        return baseDao.deleteByRoleIds(roleIds);
    }

    /**
     * 权限与角色关系
     */
    @Override
    public int deleteByPermId(Integer permId) {
        return baseDao.deleteByPermId(permId);
    }
}
