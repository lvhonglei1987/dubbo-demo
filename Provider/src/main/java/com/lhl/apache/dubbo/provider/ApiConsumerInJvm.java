package com.lhl.apache.dubbo.provider;

import com.lhl.apache.dubbo.sdk.GreetingService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.rpc.RpcContext;

/**
 * 本地服务暴露与引用
 * 远程服务暴露是在两个JVM之间，通过network来进行通信的
 * 本地服务暴露是在一个JVM内进行通信的
 * @author lvhonglei
 */
public class ApiConsumerInJvm {

    public static void exportService() {
        //1)创建ServiceConfig实例
        ServiceConfig<GreetingService> serviceConfig = new ServiceConfig<>();
        //2)设置应用程序配置
        serviceConfig.setApplication(new ApplicationConfig("first-dubbo-provider"));
        //3)设置服务注册中心信息
        RegistryConfig registryConfig = new RegistryConfig("zookeeper://127.0.0.1:2181");
        serviceConfig.setRegistry(registryConfig);
        //4)设置接口与实现类
        serviceConfig.setInterface(GreetingService.class);
        serviceConfig.setRef(new GreetingServiceImpl());
        //5)设置服务分组与版本
        serviceConfig.setVersion("1.0.0");
        serviceConfig.setGroup("dubbo");
        //6)导出服务
        serviceConfig.export();
        //7)挂起线程，避免服务停止
        System.out.println("server is started");
    }

    public static void referService() {
        //8)创建服务引用对象实例
        ReferenceConfig<GreetingService> referenceConfig = new ReferenceConfig<>();
        //9)设置服务注册中心
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        //10)设置服务接口时间和超时时间
        referenceConfig.setInterface(GreetingService.class);
        referenceConfig.setTimeout(5000);

        referenceConfig.setVersion("1.0.0");
        referenceConfig.setGroup("dubbo");
        referenceConfig.setCheck(false);

        //11)引用服务
        GreetingService greetingService = referenceConfig.get();

        //12)设置隐式参数
        RpcContext.getContext().setAttachment("company","lhl");

        //13)调用服务
        System.out.println(greetingService.sayHello("world"));

    }

    public static void main(String[] args){
        //导出服务
        exportService();
        //引用服务
        referService();
    }
}
