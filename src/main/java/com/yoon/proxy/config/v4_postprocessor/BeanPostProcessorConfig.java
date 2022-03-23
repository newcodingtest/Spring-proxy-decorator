package com.yoon.proxy.config.v4_postprocessor;

import com.yoon.proxy.app.trace.logTrace.LogTrace;
import com.yoon.proxy.config.AppV1Config;
import com.yoon.proxy.config.AppV2Config;
import com.yoon.proxy.config.v3_proxyFactory.advice.LogTraceAdvice;
import com.yoon.proxy.config.v4_postprocessor.postprocessor.PackageLogTracePostProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class BeanPostProcessorConfig {

    @Bean
    public PackageLogTracePostProcessor logTracePostProcessor(LogTrace logTrace){
        return new PackageLogTracePostProcessor("com.yoon.proxy.app",getAdvisor(logTrace));
    }

    private Advisor getAdvisor(LogTrace logTrace) {
        //pointcut
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");
        //advice
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut,advice);
    }

}
