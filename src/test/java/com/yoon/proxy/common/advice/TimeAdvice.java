package com.yoon.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class TimeAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();

        //프록시 펙토리에서 타켓을 찾아서 호출하고 그 결과를 받는다.
        Object result = invocation.proceed();

        long endTime = System.currentTimeMillis();
        long resultTime = (endTime-startTime);
        log.info("TimeProxy 종료 resultTime={}",resultTime);

        return result;
    }
}
