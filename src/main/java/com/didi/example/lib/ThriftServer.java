package com.didi.example.lib;

import com.didi.example.service.UserService;
import com.didi.example.service.impl.UserServiceImpl;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author huangjin
 *
 * TSimpleServer 是一个单线程阻塞 I/O 的 Server，它循环监听新请求的到来并对请求进行处理
 * 但一次只能接收和处理一个 Socket 连接，效率低，主要用于测试和学习，实际开发很少用到
 *
 * TNonblockingServer 是一个单线程 NIO 的 Server，NIO 通过 Selector 循环监听所有 Socket，每次 selector 结束时，
 * 处理所有就绪状态的 Socket。一个阻塞 I/O 线程一次只能处理一个 Socket 连接，而一个 NIO 线程可以处理多个 Socket 连
 * 接。虽然一个 NIO 线程可以同时接收多个 Socket 连接，但在处理任务时仍然是阻塞的
 *
 * TThreadPoolServer 模式采用阻塞 Socket 方式工作，主线程负责阻塞监听是否有新的 Socket 连接，业务交由一个线程池进行
 * 处理。该模式的处理能力受限于线程池的工作能力，当并发请求数大于线程池中的线程数时，新的请求会进入队列中排队等待处理
 *
 * THsHaServer 是 TNonBlockingServer 的子类，在 TNonBlockingServer 模式中，采用一个线程来完成对所有的 Socket
 * 监听和业务处理，造成了效率低下，而 THsHaServer 模式使用了一个线程池来专门进行业务处理
 *
 * TThreadedSelectorServer 是 Thrift 目前最高级的模式，它内部由几个部分构成：
 * 一个 AcceptThread 线程对象，专门用于处理 Socket 上的新连接
 * 若干个 SelectorThread 对象专门用于处理业务 Socket 的网络 I/O 操作，所有网络数据的读写都是由这些线程来完成的
 * 一个负载均衡器 SelectorThreadLoadBalancer 对象，主要用于 AcceptThread 线程接收到新的 Socket 连接请求时，
 * 将请求分配给 SelectorThread 线程。一个 ExecutorService 线程池，负责完成业务逻辑处理
 *
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

    public void nbCompactStart() {
        log.info("non block compact start thrift server at port {}", port);
        try (TNonblockingServerTransport serverSocket = new TNonblockingServerSocket(port)) {
            TMultiplexedProcessor processor = new TMultiplexedProcessor();
            TThreadedSelectorServer.Args serverParams = new TThreadedSelectorServer.Args(serverSocket);
            serverParams.protocolFactory(new TCompactProtocol.Factory());
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

    public void nbJsonStart() {
        log.info("non block json start thrift server at port {}", port);
        try (TNonblockingServerTransport serverSocket = new TNonblockingServerSocket(port)) {
            TMultiplexedProcessor processor = new TMultiplexedProcessor();
            TThreadedSelectorServer.Args serverParams = new TThreadedSelectorServer.Args(serverSocket);
            serverParams.protocolFactory(new TJSONProtocol.Factory());
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

    public void asyncStart() {
        log.info("non block start thrift server at port {}", port);
        try (TNonblockingServerTransport serverSocket = new TNonblockingServerSocket(port)) {
            TThreadedSelectorServer.Args serverParams = new TThreadedSelectorServer.Args(serverSocket);
            serverParams.protocolFactory(new TBinaryProtocol.Factory());
            serverParams.transportFactory(new TFramedTransport.Factory());
            serverParams.processor(new UserService.Processor<UserService.Iface>(new UserServiceImpl()));
            TServer server = new TThreadedSelectorServer(serverParams);
            server.serve();
        } catch (TTransportException e) {
            log.error(e.getMessage());
        }
    }
}
