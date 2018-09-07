package cn.hejinyo.calm.jelly.dao;

import cn.hejinyo.calm.common.basis.base.BaseDao;
import cn.hejinyo.calm.jelly.model.entity.SysDictOptionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * sys_dict_option 持久化层
 *
 * @author : HejinYo   hejinyo@gmail.com
 * @date : 2018/06/15 23:59
 */
@Mapper
public interface SysDictOptionDao extends BaseDao<SysDictOptionEntity, Integer> {
    /**
     * 修改配置属性编码
     */
    int updateCode(@Param("oldCode") String oldCode, @Param("newCode") String newCode);

    /**
     * 获取数据字典项
     */
    List<SysDictOptionEntity> getDictOptionByCode(String code);
}