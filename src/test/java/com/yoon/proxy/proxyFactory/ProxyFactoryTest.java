package com.yoon.proxy.proxyFactory;

import com.yoon.proxy.common.advice.TimeAdvice;
import com.yoon.proxy.common.service.ConcreateService;
import com.yoon.proxy.common.service.ServiceImpl;
import com.yoon.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ProxyFactoryTest {
    
    @Test
    @DisplayName("인터페이스가 있으면 JDK 동적 프록시 사용")
    void inferfaceProxy(){
        ServiceInterface target = new ServiceImpl();
        ProxyFactory factory = new ProxyFactory(target);
        factory.addAdvice(new TimeAdvice());
        ServiceInterface proxy = (ServiceInterface)factory.getProxy();
        log.info("targetClass={}",target.getClass());
        log.info("proxyClass={}",proxy.getClass());

        proxy.save();

        //프록시 패턴을 사용할 때만 사용할 수 있음
        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();
        assertThat(AopUtils.isCglibProxy(proxy)).isFalse();
    }

    @Test
    @DisplayName("구체 클래스만 있으면 CGLIB 사용")
    void concreateProxy(){
        ConcreateService target = new ConcreateService();
        ProxyFactory factory = new ProxyFactory(target);
        factory.addAdvice(new TimeAdvice());
        ConcreateService proxy = (ConcreateService)factory.getProxy();
        log.info("targetClass={}",target.getClass());
        log.info("proxyClass={}",proxy.getClass());

        proxy.call();

        //프록시 패턴을 사용할 때만 사용할 수 있음
        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
    }

    @Test
    @DisplayName("proxyTargetClass 옵션을 사용하면 인터페이스가 있어도 cglib 를 사용하고 , 클래스 기반 프록시  사용")
    void proxyTargetClass(){
        ServiceInterface target = new ServiceImpl();
        ProxyFactory factory = new ProxyFactory(target);
        factory.setProxyTargetClass(true);
        factory.addAdvice(new TimeAdvice());
        ServiceInterface proxy = (ServiceInterface)factory.getProxy();
        log.info("targetClass={}",target.getClass());
        log.info("proxyClass={}",proxy.getClass());

        proxy.save();

        //프록시 패턴을 사용할 때만 사용할 수 있음
        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
    }

}
