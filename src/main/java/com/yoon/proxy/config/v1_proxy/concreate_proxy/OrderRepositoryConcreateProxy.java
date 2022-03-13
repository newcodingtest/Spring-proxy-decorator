package com.yoon.proxy.config.v1_proxy.concreate_proxy;

import com.yoon.proxy.app.trace.TraceStatus;
import com.yoon.proxy.app.trace.logTrace.LogTrace;
import com.yoon.proxy.app.v2.OrderRepositoryV2;

public class OrderRepositoryConcreateProxy extends OrderRepositoryV2 {

    private final OrderRepositoryV2 target;
    private final LogTrace logTrace;

    public OrderRepositoryConcreateProxy(OrderRepositoryV2 target, LogTrace logTrace) {
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public void save(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderRepository.request()");
            //target 호출
            target.save(itemId);
            logTrace.end(status);

        }catch (Exception e){
            logTrace.exception(status,e);
            throw e;
        }
    }
}
