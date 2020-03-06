package com.didi.example.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author huangjin
 */
@Component
@Slf4j
public class Consumer {
    @RabbitListener(queues = "user.create")
    public void receive(Message message) {
        log.info("收到消息 : " + new String(message.getBody()));
    }
}
