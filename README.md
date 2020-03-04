# thrift-example
## thrift版本
version ~~0.13.0~~ 0.9.3
异步接口调用的时候发现libthrift-0.13.0依赖jdk9，因此调降版本到0.9.3

[wiki](http://thrift.apache.org/tutorial/java)

## 生成java文件指令
thrift -r --gen java UserService.thrift

## gradle
version 5.2.1

## guava
version 14.0