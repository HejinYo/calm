package cn.hejinyo.calm.oss.factory;

import cn.hejinyo.calm.common.basis.consts.Constant;
import cn.hejinyo.calm.oss.feign.ConfigApiService;
import cn.hejinyo.calm.oss.model.CloudStorageDTO;
import cn.hejinyo.calm.oss.service.CloudStorageService;
import cn.hejinyo.calm.oss.service.impl.QcloudCloudStorageService;
import cn.hejinyo.calm.oss.service.impl.QiniuCloudStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 文件上传Factory
 *
 * @author hejinyo
 * @date 2017/9/26 11:00
 */
@Component
public final class OSSFactory {
    private static ConfigApiService sysConfigService;

    @Autowired
    public void setDatastore(ConfigApiService sysConfigService) {
        OSSFactory.sysConfigService = sysConfigService;
    }

    public static CloudStorageService build() {
        //获取云存储配置信息
        CloudStorageDTO config = sysConfigService.getCloudConfig();
        if (config.getType() == Constant.CloudService.QCLOUD.getValue()) {
            return new QcloudCloudStorageService(config);
        } else if (config.getType() == Constant.CloudService.QINIU.getValue()) {
            return new QiniuCloudStorageService(config);
        }
        return new QcloudCloudStorageService(config);
    }
}
