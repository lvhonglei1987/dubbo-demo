package com.lhl.apache.dubbo.provider;

import com.lhl.apache.dubbo.sdk.GreetingServiceAsync;
import org.apache.dubbo.common.utils.NamedThreadFactory;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 异步实现
 * @author lvhonglei
 */
public class GreetingServiceAsyncImpl implements GreetingServiceAsync {

    //1)创建业务自定义线程池
    private final ThreadPoolExecutor lhlThreadPool = new ThreadPoolExecutor(
            8,
            16,
            1,
            TimeUnit.MILLISECONDS,
            new SynchronousQueue<>(),
            new NamedThreadFactory("lhl-thread-pool"),
            new ThreadPoolExecutor.CallerRunsPolicy());

    //2)创建服务接口，返回值是CompletableFuture
    @Override
    public CompletableFuture<String> sayHello(String name) {
        RpcContext rpcContext = RpcContext.getContext();

        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("async return");
            return "Hello " + name + " " + rpcContext.getAttachment("company");
        },lhlThreadPool);
    }
}
