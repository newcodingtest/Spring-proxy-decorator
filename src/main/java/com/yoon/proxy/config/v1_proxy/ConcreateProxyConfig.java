package com.yoon.proxy.config.v1_proxy;

import com.yoon.proxy.app.trace.logTrace.LogTrace;
import com.yoon.proxy.app.v2.OrderControllerV2;
import com.yoon.proxy.app.v2.OrderRepositoryV2;
import com.yoon.proxy.app.v2.OrderServiceV2;
import com.yoon.proxy.config.v1_proxy.concreate_proxy.OrderControllerConcreateProxy;
import com.yoon.proxy.config.v1_proxy.concreate_proxy.OrderRepositoryConcreateProxy;
import com.yoon.proxy.config.v1_proxy.concreate_proxy.OrderServiceConcreateProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreateProxyConfig {

    @Bean
    public OrderControllerV2 orderControllerV2(LogTrace logTrace){
        OrderControllerV2 controllerV2Impl = new OrderControllerV2(orderServiceV2(logTrace));
        return new OrderControllerConcreateProxy(controllerV2Impl, logTrace);

    }

    @Bean
    public OrderServiceV2 orderServiceV2(LogTrace logTrace){
        OrderServiceV2 serviceV2Impl = new OrderServiceV2(orderRepositoryV2(logTrace));
        return new OrderServiceConcreateProxy(serviceV2Impl, logTrace);
    }

    @Bean
    public OrderRepositoryV2 orderRepositoryV2(LogTrace logTrace){
            OrderRepositoryV2 repositoryImpl = new OrderRepositoryV2();
            return new OrderRepositoryConcreateProxy(repositoryImpl,logTrace);
    }
}
