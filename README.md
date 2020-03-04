# thrift-example
## thrift版本
version ~~0.13.0~~ 0.9.3

异步接口调用的时候发现libthrift-0.13.0依赖jdk9，因此调降版本到0.9.3

## 生成java文件指令
thrift -r --gen java UserService.thrift

## gradle
version 5.2.1

## guava
version 14.0

## client
[thrift-client-example](https://github.com/hjinjsj/thrift-client-example)

## 参考wiki：
官方文档：<http://thrift.apache.org/tutorial/java>

基本说明：<https://www.ibm.com/developerworks/cn/java/j-lo-apachethrift/index.html>

多接口服务：<https://blog.csdn.net/hivon/article/details/11681977>

server比较：<https://www.jianshu.com/p/047f9a5385df>、<https://zhuanlan.zhihu.com/p/31110044>