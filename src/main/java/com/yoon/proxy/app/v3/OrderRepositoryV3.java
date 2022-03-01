package com.yoon.proxy.app.v3;


import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV3 {

    public void save(String itemId){
        if(itemId.equals("ex")){
            throw new IllegalStateException("예외 발생");
        }
        sleep(100);
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
