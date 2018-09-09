package cn.hejinyo.calm.oss.feign;

import cn.hejinyo.calm.common.basis.consts.ConfigConstant;
import cn.hejinyo.calm.common.basis.consts.Constant;
import cn.hejinyo.calm.common.web.shiro.AuthCheckResult;
import cn.hejinyo.calm.oss.feign.fallback.ConfigApiServiceFallbackImpl;
import cn.hejinyo.calm.oss.model.CloudStorageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 登陆逻辑查询用户信息
 *
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/8/29 21:55
 */
@FeignClient(name = "calm-jelly", fallback = ConfigApiServiceFallbackImpl.class)
public interface ConfigApiService {

    /**
     * 获取云存储配置
     */
    @GetMapping(value = Constant.SERVER_API + "/config/getConfig/" + ConfigConstant.CLOUD_STORAGE_CONFIG_KEY)
    CloudStorageDTO getCloudConfig();
}
