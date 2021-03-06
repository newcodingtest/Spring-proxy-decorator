package com.yoon.proxy;

import com.yoon.proxy.config.v1_proxy.ConcreateProxyConfig;
import com.yoon.proxy.config.v1_proxy.InterfaceProxyConfig;
import com.yoon.proxy.app.trace.logTrace.LogTrace;
import com.yoon.proxy.app.trace.logTrace.ThreadLocalLogTrace;
import com.yoon.proxy.config.v2_dynamicproxy.DynamicProxyBasicConfig;
import com.yoon.proxy.config.v2_dynamicproxy.DynamicProxyFilterConfig;
import com.yoon.proxy.config.v3_proxyFactory.ProxyFactoryConfigV1;
import com.yoon.proxy.config.v3_proxyFactory.ProxyFactoryConfigV2;
import com.yoon.proxy.config.v4_postprocessor.BeanPostProcessorConfig;
import com.yoon.proxy.config.v5_autoproxy.AutoProxyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

//@Import(AppV1Config.class)
//@Import({AppV1Config.class,AppV2Config.class})
//@Import(InterfaceProxyConfig.class)
//@Import(ConcreateProxyConfig.class)
//@Import(DynamicProxyBasicConfig.class)
//@Import(DynamicProxyFilterConfig.class)
//@Import(ProxyFactoryConfigV1.class)
//@Import(ProxyFactoryConfigV2.class)
//@Import(BeanPostProcessorConfig.class)
@Import(AutoProxyConfig.class)
@SpringBootApplication(scanBasePackages = "com.yoon.proxy.app")
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

	@Bean
	public LogTrace logTrace(){
		return new ThreadLocalLogTrace();
	}
}
