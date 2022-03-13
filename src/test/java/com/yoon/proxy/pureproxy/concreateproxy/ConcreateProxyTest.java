package com.yoon.proxy.pureproxy.concreateproxy;

import com.yoon.proxy.pureproxy.concreateproxy.code.ConcreateClient;
import com.yoon.proxy.pureproxy.concreateproxy.code.ConcreateLogic;
import com.yoon.proxy.pureproxy.concreateproxy.code.TimeProxy;
import org.junit.jupiter.api.Test;

public class ConcreateProxyTest {

    @Test
    void noProxy(){
        ConcreateLogic concreateLogic = new ConcreateLogic();
        ConcreateClient client = new ConcreateClient(concreateLogic);
        client.execute();
    }

    @Test
    void addProxy(){
        ConcreateLogic concreateLogic = new ConcreateLogic();
        TimeProxy proxy = new TimeProxy(concreateLogic);
        ConcreateClient client = new ConcreateClient(proxy);
        client.execute();

    }
}
