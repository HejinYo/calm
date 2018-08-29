package cn.hejinyo.calm.gateway.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/8/27 0:00
 */
@RestController
@RequestMapping("/res")
public class ResourceController {

    @GetMapping("/open/{id}")
    public String open(@PathVariable("id") String id) {
        return "我是开放资源" + id;
    }


    @GetMapping("/auth/{id}")
    public String auth(@PathVariable("id") String id) {
        return "我是私有资源" + id;
    }
}
