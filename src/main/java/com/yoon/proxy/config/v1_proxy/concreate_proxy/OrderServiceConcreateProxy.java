package com.yoon.proxy.config.v1_proxy.concreate_proxy;

import com.yoon.proxy.app.trace.TraceStatus;
import com.yoon.proxy.app.trace.logTrace.LogTrace;
import com.yoon.proxy.app.v2.OrderRepositoryV2;
import com.yoon.proxy.app.v2.OrderServiceV2;

public class OrderServiceConcreateProxy extends OrderServiceV2 {

    private final OrderServiceV2 target;
    private final LogTrace logTrace;


    public OrderServiceConcreateProxy(OrderServiceV2 target, LogTrace logTrace) {
        super(null);
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderService.orderItem()");
            //target 호출
            target.orderItem(itemId);
            logTrace.end(status);

        }catch (Exception e){
            logTrace.exception(status,e);
            throw e;
        }
    }
}
