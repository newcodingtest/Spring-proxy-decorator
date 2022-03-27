package com.yoon.proxy.config.v5_autoproxy;

import com.yoon.proxy.app.trace.logTrace.LogTrace;
import com.yoon.proxy.config.AppV1Config;
import com.yoon.proxy.config.AppV2Config;
import com.yoon.proxy.config.v3_proxyFactory.advice.LogTraceAdvice;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class AutoProxyConfig {


   // @Bean
    public Advisor advisor1(LogTrace logTrace) {
        //pointcut
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");
        //advice
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut,advice);
    }

    @Bean
    public Advisor advisor2(LogTrace logTrace) {
        //pointcut
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.yoon.proxy.app..*(..)) && !execution(* com.yoon.proxy.app..noLog(..))");
        //advice
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut,advice);
    }
}
