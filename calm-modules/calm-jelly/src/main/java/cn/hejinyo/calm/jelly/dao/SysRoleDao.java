package cn.hejinyo.calm.jelly.dao;

import cn.hejinyo.calm.common.basis.base.BaseDao;
import cn.hejinyo.calm.jelly.model.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * sys_role 持久化层
 *
 * @author : HejinYo   hejinyo@gmail.com
 * @date : 2018/06/14 20:33
 */
@Mapper
public interface SysRoleDao extends BaseDao<SysRoleEntity, Integer> {
    /**
     * 角色列表下拉选择select
     */
    List<SysRoleEntity> findDropList();

    /**
     * 根据角色Id列表获取角色信息列表
     */
    List<SysRoleEntity> findListByRoleIdList(@Param("roleIdList") List<Integer> roleIdList);
}