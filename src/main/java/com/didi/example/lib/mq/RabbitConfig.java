package com.didi.example.lib.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author huangjin
 */
@Configuration
public class RabbitConfig {
    static final String userExchange = "user";

    static final String createUserQueueName = "user.create";

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue getCreateUserQueue() {
        return new Queue(createUserQueueName);
    }

    @Bean
    public DirectExchange getUserExchange() {
        return new DirectExchange(userExchange);
    }

    @Bean
    public Binding bindingUser() {
        return BindingBuilder.bind(getCreateUserQueue())
            .to(getUserExchange())
            .with(createUserQueueName);
    }
}
