package com.didi.example.lib;

import com.didi.example.service.UserService;
import com.didi.example.service.impl.UserServiceImpl;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.*;
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
        log.info("start thrift server at port {}", port);
        try (TServerTransport t = new TServerSocket(port);){
            TMultiplexedProcessor processor = new TMultiplexedProcessor();
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(t).processor(processor));
            processor.registerProcessor(
                "UserService",
                new UserService.Processor<UserService.Iface>(new UserServiceImpl())
            );
            server.serve();
        } catch (TTransportException e) {
            log.error(e.getMessage());
        }
    }

    public void nbStart() {
        log.info("non block start thrift server at port {}", port);
        try (TNonblockingServerTransport serverSocket = new TNonblockingServerSocket(port)) {
            TMultiplexedProcessor processor = new TMultiplexedProcessor();
            TThreadedSelectorServer.Args serverParams = new TThreadedSelectorServer.Args(serverSocket);
            serverParams.protocolFactory(new TBinaryProtocol.Factory());
            serverParams.transportFactory(new TFramedTransport.Factory());
            serverParams.processor(processor);
            processor.registerProcessor(
                "UserService",
                new UserService.Processor<UserService.Iface>(new UserServiceImpl())
            );
            TServer server = new TThreadedSelectorServer(serverParams);
            server.serve();
        } catch (TTransportException e) {
            log.error(e.getMessage());
        }
    }
}
