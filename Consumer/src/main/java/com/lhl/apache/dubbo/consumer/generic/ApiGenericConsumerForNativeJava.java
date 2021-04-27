package com.lhl.apache.dubbo.consumer.generic;

import org.apache.dubbo.common.Constants;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.common.io.UnsafeByteArrayInputStream;
import org.apache.dubbo.common.io.UnsafeByteArrayOutputStream;
import org.apache.dubbo.common.serialize.Serialization;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;

import java.io.IOException;
import java.io.Serializable;

/**
 * generic = nativejava
 * @author lvhonglei
 */
public class ApiGenericConsumerForNativeJava {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //1)泛型固定参数GenericService
        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(new ApplicationConfig("first-dubbo-consumer"));
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        referenceConfig.setVersion("1.0.0");
        referenceConfig.setGroup("dubbo");

        //2)设置为泛化引用
        referenceConfig.setInterface("com.lhl.apache.dubbo.sdk.GreetingService");
        referenceConfig.setGeneric("nativejava");

        //3)用GenericService替换所有接口
        GenericService genericService = referenceConfig.get();
        UnsafeByteArrayOutputStream out = new UnsafeByteArrayOutputStream();

        //4)泛化调用
        ExtensionLoader.getExtensionLoader(Serialization.class)
                .getExtension(Constants.GENERIC_SERIALIZATION_NATIVE_JAVA)
                .serialize(null,out)
                .writeObject("world");

        Object result = genericService.$invoke("sayHello",new String[]{"java.lang.String"},new Object[]{out.toByteArray()});

        //5)打印结果，需要反序列化
        UnsafeByteArrayInputStream in = new UnsafeByteArrayInputStream((byte[]) result);
        result = ExtensionLoader.getExtensionLoader(Serialization.class)
                .getExtension(Constants.GENERIC_SERIALIZATION_NATIVE_JAVA)
                .deserialize(null, in).readObject();

        System.out.println(result);
    }
}
