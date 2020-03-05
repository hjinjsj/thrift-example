package com.didi.example;

import com.didi.example.lib.ThriftServer;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author huangjin
 */
@SpringBootApplication
@MapperScan("com.didi.example.mapper")
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
//        thriftServer.start();
//        thriftServer.nbStart();
//        thriftServer.nbCompactStart();
        thriftServer.nbJsonStart();
//        thriftServer.asyncStart();
    }

}
