package com.didi.example.listener;


import com.didi.example.event.CreateUserEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author huangjin
 */
@Component
@Slf4j
public class CreateUserListener  implements ApplicationListener<CreateUserEvent> {
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void onApplicationEvent(CreateUserEvent createUserEvent) {
        log.info("receive create user event: {}", createUserEvent.getUser().toString());
        rabbitTemplate.convertAndSend( "user" , "user.create" , createUserEvent.getUser() );
    }
}
