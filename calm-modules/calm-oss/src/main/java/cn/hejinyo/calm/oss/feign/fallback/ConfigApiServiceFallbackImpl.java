package cn.hejinyo.calm.oss.feign.fallback;

import cn.hejinyo.calm.oss.feign.ConfigApiService;
import cn.hejinyo.calm.oss.model.CloudStorageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/8/29 21:56
 */
@Service
@Slf4j
public class ConfigApiServiceFallbackImpl implements ConfigApiService {

    /**
     * 获取云存储配置
     */
    @Override
    public CloudStorageDTO getCloudConfig() {
        log.error("获取云存储配置异常");
        return null;
    }
}
