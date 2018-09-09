package cn.hejinyo.calm.jelly.listener;

import cn.hejinyo.calm.common.basis.consts.RabbitConstant;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 消费者
 *
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/7/12 21:40
 */
@Component
@Slf4j
public class RabbiMqListener {
    /**
     * DIRECT模式.
     */
    @RabbitListener(queues = {RabbitConstant.calmLog.QUEUE})
    public void message(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        log.debug("DIRECT " + new String(message.getBody()));
    }

}
