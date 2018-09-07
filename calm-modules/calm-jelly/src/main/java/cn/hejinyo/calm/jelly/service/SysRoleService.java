package cn.hejinyo.calm.jelly.service;

import cn.hejinyo.calm.common.basis.base.BaseService;
import cn.hejinyo.calm.jelly.model.entity.SysRoleEntity;

import java.util.List;

/**
 * @author : HejinYo   hejinyo@gmail.com
 * @date : 2017/6/17 17:04
 */
public interface SysRoleService extends BaseService<SysRoleEntity, Integer> {

    /**
     * 角色列表下拉选择select
     */
    List<SysRoleEntity> getDropList();

    /**
     * 根据角色Id列表获取角色信息列表
     */
    List<SysRoleEntity> getListByRoleIdList(List<Integer> roleIdList);
}
