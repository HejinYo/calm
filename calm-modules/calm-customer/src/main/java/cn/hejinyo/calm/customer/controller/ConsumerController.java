package cn.hejinyo.calm.customer.controller;

import cn.hejinyo.calm.customer.feign.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/4/18 23:30
 */
@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProviderService providerService;

    @GetMapping("/joke1")
    public String joke1() {
        return restTemplate.getForEntity("http://calm-provider/provider", String.class).getBody();
    }

    @GetMapping("/joke2")
    public String joke2() {
        return providerService.get();
    }

    @GetMapping("/joke3")
    public String joke3() {
        return providerService.post();
        //return helloService.helloService();
    }


}
