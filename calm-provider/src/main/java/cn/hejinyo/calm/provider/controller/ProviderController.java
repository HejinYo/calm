package cn.hejinyo.calm.provider.controller;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/7/18 23:00
 */
@RestController
@RequestMapping(value = "/provider")
public class ProviderController {

    @GetMapping
    public String get() {
        return LocalDateTime.now().toString();
    }
}
