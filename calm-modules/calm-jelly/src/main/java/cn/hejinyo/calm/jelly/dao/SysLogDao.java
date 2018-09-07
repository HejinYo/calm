package cn.hejinyo.calm.jelly.dao;

import cn.hejinyo.calm.common.basis.base.BaseDao;
import cn.hejinyo.calm.jelly.model.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * sys_log 持久化层
 * 
 * @author : HejinYo   hejinyo@gmail.com 
 * @date : 2018/06/13 23:29
 */
@Mapper
public interface SysLogDao extends BaseDao<SysLogEntity, Integer> {
}