package cn.hejinyo.calm.jelly.api;

import cn.hejinyo.calm.common.basis.consts.Constant;
import cn.hejinyo.calm.jelly.service.SysConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/9/8 19:57
 */
@RestController
@RequestMapping(Constant.SERVER_API + "/config")
@Slf4j
public class SysConfigApi {
    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 根据配置编码获取配置详情
     */
    @GetMapping(value = "/getConfig/{code}")
    public Object getConfig(@PathVariable("code") String code) {
        return sysConfigService.getConfig(code);
    }

}
