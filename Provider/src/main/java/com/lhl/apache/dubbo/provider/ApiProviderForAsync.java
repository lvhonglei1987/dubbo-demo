package com.lhl.apache.dubbo.provider;

import com.lhl.apache.dubbo.sdk.GreetingServiceAsync;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.io.IOException;

/**
 *
 * @author lvhonglei
 */
public class ApiProviderForAsync {

    public static void main(String[] args) throws IOException {

        //1)创建服务发布实例，并进行设置
        ServiceConfig<GreetingServiceAsync> serviceConfig = new ServiceConfig<>();
        serviceConfig.setApplication(new ApplicationConfig("first-dubbo-provider"));
        RegistryConfig registryConfig = new RegistryConfig("zookeeper://127.0.0.1:2181");
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setInterface(GreetingServiceAsync.class);
        serviceConfig.setRef(new GreetingServiceAsyncImpl());
        serviceConfig.setVersion("1.0.0");
        serviceConfig.setGroup("dubbo");

        //2)设置线程池策略
        //HashMap<String,String> parameters = new HashMap<>(200);
        //parameters.put("threadpool","mythreadpool");
        //serviceConfig.setParameters(parameters);

        //3)导出服务
        serviceConfig.export();

        //4)阻塞线程
        System.out.println("Server is Started");
        System.in.read();
    }
}
