package com.lhl.apache.dubbo.provider;

import com.lhl.apache.dubbo.sdk.GreetingServiceAsync;
import org.apache.dubbo.common.utils.NamedThreadFactory;
import org.apache.dubbo.rpc.AsyncContext;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 使用AsyncContext实现异步
 * @author lvhonglei
 */
public class GreetingServiceAsyncContextImpl implements GreetingServiceAsync {

    //1)创建业务线程池
    private final ThreadPoolExecutor lhlContextPool = new ThreadPoolExecutor(
            8,
            16,
            1,
            TimeUnit.MILLISECONDS,
            new SynchronousQueue<>(),
            new NamedThreadFactory("lhl-context-pool"),
            new ThreadPoolExecutor.CallerRunsPolicy());

    //2）创建服务处理接口，返回值是CompletableFuture
    @Override
    public CompletableFuture<String> sayHello(String name) {
        final AsyncContext asyncContext = RpcContext.startAsync();
        lhlContextPool.execute(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            asyncContext.write("Hello " + name + " " + RpcContext.getContext().getAttachment("company"));
        });
        return null;
    }
}
