# thrift-example
## thrift版本
version ~~0.13.0~~ 0.9.3

异步接口调用的时候发现libthrift-0.13.0依赖jdk9，因此调降版本到0.9.3

## 生成java文件指令
thrift -r --gen java UserService.thrift

## gradle
version 5.2.1

## guava
version ~~14.0~~ 18.0

sharding-jdbc依赖guava，因此版本需要升级

## client
[thrift-client-example](https://github.com/hjinjsj/thrift-client-example)

## mybatis-sprint-boot-starter
version 1.3.1

特别注意：注入thrift的processor一定要是注解实例化的不能用new，否则回出现mapper注入失败的问题

## sharding-jdbc
<https://shardingsphere.apache.org/document/current/cn/manual/sharding-jdbc/configuration/config-spring-boot/>

版本不同配置文件不同，对于hikaricp配置db的url字段为jdbc-url，其它的为url，需要特别注意

## 参考wiki：
官方文档：<http://thrift.apache.org/tutorial/java>、<https://people.apache.org/~thejas/thrift-0.9/javadoc/>

基本说明：<https://www.ibm.com/developerworks/cn/java/j-lo-apachethrift/index.html>

多接口服务：<https://blog.csdn.net/hivon/article/details/11681977>

server比较：<https://www.jianshu.com/p/047f9a5385df>、<https://zhuanlan.zhihu.com/p/26993406>、<https://zhuanlan.zhihu.com/p/31110044>

PDF: [Apache THRIFT: A Much Needed Tutorial](./docs/thrift_BSD_08_2013.8-18.pdf)