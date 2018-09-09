package cn.hejinyo.calm.jelly.controller;

import cn.hejinyo.calm.common.basis.consts.RabbitConstant;
import cn.hejinyo.calm.common.basis.utils.Result;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/7/12 21:41
 */
@RestController
@RequestMapping("/test/rabbit")
public class RabbitSendController {
    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 测试Direct模式.
     */
    @RequestMapping("/direct")
    public Result direct(String p) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitConstant.calmLog.EXCHANGE, RabbitConstant.calmLog.KEY, p, correlationData);
        return Result.ok();
    }
}