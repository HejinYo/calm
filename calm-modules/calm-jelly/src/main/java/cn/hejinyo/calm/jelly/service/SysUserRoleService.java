package cn.hejinyo.calm.jelly.service;

import cn.hejinyo.calm.common.basis.base.BaseService;
import cn.hejinyo.calm.jelly.model.entity.SysUserRoleEntity;

import java.util.List;

/**
 * @author : HejinYo   hejinyo@gmail.com
 * @date : 2017/6/17 17:04
 */
public interface SysUserRoleService extends BaseService<SysUserRoleEntity, Integer> {

    /**
     * 根据用户拥有的角色ID列表
     */
    List<Integer> getRoleIdListByUserId(Integer userId);

    /**
     * 保存用户角色关系
     */
    int save(Integer userId, List<Integer> roleIdList);

    /**
     * 删除用户与角色关系
     */
    int deleteByUserId(Integer userId);
}
