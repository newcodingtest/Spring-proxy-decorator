package com.yoon.proxy.config.v1_proxy.concreate_proxy;

import com.yoon.proxy.app.trace.TraceStatus;
import com.yoon.proxy.app.trace.logTrace.LogTrace;
import com.yoon.proxy.app.v2.OrderControllerV2;
import com.yoon.proxy.app.v2.OrderServiceV2;

public class OrderControllerConcreateProxy extends OrderControllerV2 {

    private final OrderControllerV2 target;
    private final LogTrace logTrace;

    public OrderControllerConcreateProxy(OrderControllerV2 target, LogTrace logTrace) {
        super(null);
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderController.request()");
            //target 호출
            String result = target.request(itemId);
            logTrace.end(status);
            return result;
        }catch (Exception e){
            logTrace.exception(status,e);
            throw e;
        }
    }
}
