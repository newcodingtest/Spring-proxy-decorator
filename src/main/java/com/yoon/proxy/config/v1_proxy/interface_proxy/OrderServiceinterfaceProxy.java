package com.yoon.proxy.config.v1_proxy.interface_proxy;

import com.yoon.proxy.app.trace.TraceStatus;
import com.yoon.proxy.app.trace.logTrace.LogTrace;
import com.yoon.proxy.app.v1.OrderServiceV1;
import lombok.RequiredArgsConstructor;

;@RequiredArgsConstructor
public class OrderServiceinterfaceProxy implements OrderServiceV1 {

    private final OrderServiceV1 target;
    private final LogTrace logTrace;

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
