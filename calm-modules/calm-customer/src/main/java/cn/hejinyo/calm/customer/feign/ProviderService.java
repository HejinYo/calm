package cn.hejinyo.calm.customer.feign;

import cn.hejinyo.calm.customer.feign.fallback.ProviderServiceFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/7/18 23:34
 */
@FeignClient(name = "calm-provider", fallback = ProviderServiceFallbackImpl.class)
public interface ProviderService {

    @GetMapping("/provider")
    String get();


    @GetMapping("/dd")
    String post();
}
