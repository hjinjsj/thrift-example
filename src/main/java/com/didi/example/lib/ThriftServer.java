package com.didi.example.lib;

import com.didi.example.service.UserService;
import com.didi.example.service.impl.UserServiceImpl;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author huangjin
 */
@Component
@Data
@Slf4j
@ConfigurationProperties(prefix = "thrift")
public class ThriftServer {
    private int port;

    public void start(){
        try {
            TMultiplexedProcessor processor = new TMultiplexedProcessor();
            TServerTransport t = new TServerSocket(port);
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(t).processor(processor));
            processor.registerProcessor(
                "UserService",
                new UserService.Processor<UserService.Iface>(new UserServiceImpl())
            );
            log.info("run thrift server at port {}", port);
            server.serve();
        } catch (TTransportException e) {
            log.error(e.getMessage());
        }
    }
}
