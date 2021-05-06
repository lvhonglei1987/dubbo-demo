# dubbo-demo
Apache Dubbo Demo

# 环境
- MAC系统
- JDK1.8
- Maven3.2.6
- Zookeeper 3.4.14
- Apache Dubbo 2.7.0
- FastJson 1.2.75
- curator 2.12.0

# Consumer
> 消费方的所有demo

# Provider
> 生产方的所有demo

# SDK
> 用来存放服务接口，是一个二方包

# Q&A
> zookeeper 与 curator有版本问题，需要兼容，否则报错

# gitHub
> https://github.com/lvhonglei1987/dubbo-demo.git

# zookeeper
## 1.Mac安装zookeeper
 [Apache Zookeeper Download](http://archive.apache.org/dist/zookeeper/)

- step1 点击上面的链接进行下载
- step2 解压文件
- step3 设置环境变量
> vi ~/.bash_profile 修改文件，添加如下代码，
```bash
export ZK_HOME=/Users/lvhonglei/Downloads/zookeeper-3.4.14/
export PATH=$PATH:$M2_HOME/bin:$JETTY_HOME:$JAVA_HOME/bin:$ZK_HOME/bin
```
- step4 使环境变量生效
> source ~/.bash_profile
- step5 启动zookeeper服务
> zkServer.sh start
- step6 查看启动状态
> zkServer.sh status
- step7 停止zookeeper服务
> zkServer.sh stop


