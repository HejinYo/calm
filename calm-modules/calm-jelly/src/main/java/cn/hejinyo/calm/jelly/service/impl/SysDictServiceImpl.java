package cn.hejinyo.calm.jelly.service.impl;

import cn.hejinyo.calm.common.basis.base.BaseServiceImpl;
import cn.hejinyo.calm.common.basis.consts.Constant;
import cn.hejinyo.calm.common.basis.utils.PageQuery;
import cn.hejinyo.calm.common.basis.utils.PojoConvertUtil;
import cn.hejinyo.calm.common.web.exception.InfoException;
import cn.hejinyo.calm.common.web.utils.ShiroUtils;
import cn.hejinyo.calm.jelly.dao.SysDictDao;
import cn.hejinyo.calm.jelly.dao.SysDictOptionDao;
import cn.hejinyo.calm.jelly.model.entity.SysDictEntity;
import cn.hejinyo.calm.jelly.model.entity.SysDictOptionEntity;
import cn.hejinyo.calm.jelly.model.dto.DictDTO;
import cn.hejinyo.calm.jelly.service.SysDictService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据字典管理
 *
 * @author : heshuangshuang
 * @date : 2018/4/10 17:37
 */
@Service
public class SysDictServiceImpl extends BaseServiceImpl<SysDictDao, SysDictEntity, Integer> implements SysDictService {

    @Autowired
    private SysDictOptionDao sysDictOptionDao;

    /**
     * 获取数据字典项
     */
    @Override
    public List<DictDTO> getDictOptionByCode(String code) {
        //查询数据类性
        SysDictEntity optionEntity = baseDao.findByCode(code);
        List<SysDictOptionEntity> list = sysDictOptionDao.getDictOptionByCode(code);
        List<DictDTO> dictList = new ArrayList<>();
        list.forEach(value -> {
            DictDTO dict = new DictDTO();
            dict.setLabel(value.getLabel());
            if (Constant.DataType.INTEGER.equals(optionEntity.getType())) {
                //整型
                dict.setValue(Integer.valueOf(value.getValue()));
            } else if (Constant.DataType.DOUBLE.equals(optionEntity.getType())) {
                //浮点型
                dict.setValue(Double.valueOf(value.getValue()));
            } else if (Constant.DataType.BOOLEAN.equals(optionEntity.getType())) {
                //布尔型
                dict.setValue(Boolean.valueOf(value.getValue()));
            } else {
                dict.setValue(value.getValue());
            }
            dictList.add(dict);
        });
        return dictList;
    }

    /**
     * 查询字典目录列表
     */
    @Override
    public List<SysDictEntity> getDictList() {
        return baseDao.findAllList();
    }

    /**
     * 字典目录信息
     */
    @Override
    public SysDictEntity findOne(Integer id) {
        return baseDao.findOne(id);
    }

    /**
     * 保存字典目录
     */
    @Override
    public int save(SysDictEntity configDir) {
        SysDictEntity newConfig = PojoConvertUtil.convert(configDir, SysDictEntity.class);
        newConfig.setCreateId(ShiroUtils.getUserId());
        return baseDao.save(newConfig);
    }

    /**
     * 修改字典目录
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(Integer id, SysDictEntity config) {
        SysDictEntity newConfig = PojoConvertUtil.convert(config, SysDictEntity.class);
        newConfig.setId(id);
        newConfig.setUpdateId(ShiroUtils.getUserId());
        SysDictEntity oldConfig = baseDao.findOne(id);

        //检查是否有具体字典，有具体字典不允许修改类型
        if (!oldConfig.getType().equals(newConfig.getType())) {
            SysDictOptionEntity configOption = new SysDictOptionEntity();
            configOption.setCode(oldConfig.getCode());
            if (sysDictOptionDao.count(configOption) > 0) {
                throw new InfoException("存在字典属性，不允许修改字典类型");
            }
        }

        //修改CODE,字典属性同步修改
        if (!oldConfig.getCode().equals(newConfig.getCode())) {
            sysDictOptionDao.updateCode(oldConfig.getCode(), newConfig.getCode());
        }

        return baseDao.update(newConfig);
    }

    /**
     * 删除字典目录
     */
    @Override
    public int delete(Integer id) {
        SysDictEntity oldConfig = baseDao.findOne(id);
        //检查是否有具体字典，有具体字典不允许删除
        SysDictOptionEntity configOption = new SysDictOptionEntity();
        configOption.setCode(oldConfig.getCode());
        if (sysDictOptionDao.count(configOption) > 0) {
            throw new InfoException("存在字典属性，不允许删除");
        }

        return baseDao.delete(id);
    }

    /**
     * 字典属性分页查询
     */
    @Override
    public List<SysDictOptionEntity> optionFindPage(PageQuery pageQuery) {
        PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize(), pageQuery.getOrder());
        return sysDictOptionDao.findPage(pageQuery);
    }

    /**
     * 保存字典配置
     */
    @Override
    public int saveOption(SysDictOptionEntity option) {
        SysDictOptionEntity newOption = PojoConvertUtil.convert(option, SysDictOptionEntity.class);
        newOption.setCreateId(ShiroUtils.getUserId());
        return sysDictOptionDao.save(newOption);
    }

    /**
     * 修改字典配置
     */
    @Override
    public int updateOption(SysDictOptionEntity option) {
        SysDictOptionEntity newOption = PojoConvertUtil.convert(option, SysDictOptionEntity.class);
        newOption.setUpdateId(ShiroUtils.getUserId());
        return sysDictOptionDao.update(newOption);
    }

    /**
     * 字典属性信息
     */
    @Override
    public SysDictOptionEntity findOneOption(Integer id) {
        return sysDictOptionDao.findOne(id);
    }

    /**
     * 删除字典属性
     */
    @Override
    public int deleteOption(Integer id) {
        return sysDictOptionDao.delete(id);
    }
}
