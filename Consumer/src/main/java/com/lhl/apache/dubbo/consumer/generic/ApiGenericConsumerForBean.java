package com.lhl.apache.dubbo.consumer.generic;

import org.apache.dubbo.common.beanutil.JavaBeanDescriptor;
import org.apache.dubbo.common.beanutil.JavaBeanSerializeUtil;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;

/**
 * generic = bean
 * @author lvhonglei
 */
public class ApiGenericConsumerForBean {
    public static void main(String[] args){
        //1) 引用类型实例，并设置
        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(new ApplicationConfig("first-dubbo-consumer"));
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        referenceConfig.setVersion("1.0.0");
        referenceConfig.setGroup("dubbo");
        //加上超时时间
        referenceConfig.setTimeout(30000);

        //2)设置泛化引用，并且泛化类型为bean
        referenceConfig.setInterface("com.lhl.apache.dubbo.sdk.GreetingService");
        referenceConfig.setGeneric("bean");

        //3)用com.lhl.apache.dubbo.sdk.GreetingService替换所有接口
        GenericService genericService = referenceConfig.get();

        //4)泛化调用
        JavaBeanDescriptor param = JavaBeanSerializeUtil.serialize("world");
        Object result = genericService.$invoke("sayHello",new String[]{"java.lang.String"},new Object[]{param});

        //5)结果反序列化
        result = JavaBeanSerializeUtil.deserialize((JavaBeanDescriptor) result);
        System.out.println(result);
    }
}
