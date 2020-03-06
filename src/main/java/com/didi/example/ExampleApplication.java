package com.didi.example;

import com.didi.example.lib.ThriftServer;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;


/**
 * @author huangjin
 * 自动配置
 * 1. RabbitAutoConfiguration
 * 2. 自动配置了连接工厂ConnectionFactory
 * 3. RabbitProperties 封装了RabbitMQ的配置
 * 4. RabbitTemplate : 给RabbitMQ发送和接受消息
 * 5. AmqpAdmin : RabbitMQ系统管理功能组件
 * 6. @EnableRabbit + @RabbitListener
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.didi.example.mapper")
@EnableRabbit
@Slf4j
public class ExampleApplication implements ApplicationRunner {
    @Autowired
    private ThriftServer thriftServer;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplicationBuilder(ExampleApplication.class)
            .web(false)
            .bannerMode(Banner.Mode.OFF)
            .build();
        app.run(args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("start thrift example app.");
//        thriftServer.start();
//        thriftServer.nbStart();
//        thriftServer.nbCompactStart();
        thriftServer.nbJsonStart();
//        thriftServer.asyncStart();
    }
}
