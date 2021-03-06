package cn.hejinyo.calm.jelly.dao;

import cn.hejinyo.calm.common.basis.base.BaseDao;
import cn.hejinyo.calm.jelly.model.entity.SysResourceEntity;
import cn.hejinyo.calm.jelly.model.dto.RoutersMenuDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * sys_resource 持久化层
 *
 * @author : HejinYo   hejinyo@gmail.com
 * @date : 2018/06/09 16:46
 */
@Mapper
public interface SysResourceDao extends BaseDao<SysResourceEntity, Integer> {
    /**
     * 查询所有菜单列表
     */
    List<RoutersMenuDTO> findAllMenuList();

    /**
     * 查询用户菜单列表
     */
    List<RoutersMenuDTO> findUserMenuList(@Param("userId") Integer userId);

    /**
     * 查询所有资源列表
     */
    List<SysResourceEntity> findAllResourceList();

    /**
     * 获取系统所有有效资源列表，状态正常
     */
    List<SysResourceEntity> findValidResourceList();

    /**
     * 查询父资源所有子资源列表
     */
    List<SysResourceEntity> findListByParentId(Integer inResId);

    /**
     * 修改父节点及其排序号
     */
    int updateParentIdAndSeq(@Param("resId") Integer resId, @Param("parentId") Integer parentId, @Param("seq") Integer seq);

    /**
     * 修改进入节点所有排序
     */
    int updateInnerAllSeq(List<SysResourceEntity> childList);
}