package com.didi.example;

import com.didi.example.lib.ThriftServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author huangjin
 */
@SpringBootApplication
@Slf4j
public class ExampleApplication implements ApplicationRunner {
    @Autowired
    private ThriftServer thriftServer;

    public static void main(String[] args) {
        new SpringApplicationBuilder(ExampleApplication.class)
            .web(false)
            .bannerMode(Banner.Mode.OFF)
            .run(args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("start thrift example app.");
//        thriftServer.nbStart();
        thriftServer.start();

    }

}
