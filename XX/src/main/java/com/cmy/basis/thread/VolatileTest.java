package com.cmy.basis.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileTest extends Thread {

    public static volatile boolean b = true;
    static volatile AtomicInteger count = new AtomicInteger();
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            if (b) {
                count.incrementAndGet();
                b = false;
            } else {
                count.decrementAndGet();
                b = true;
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        VolatileTest v1 = new VolatileTest();
        VolatileTest v2 = new VolatileTest();
        VolatileTest v3 = new VolatileTest();
        v1.start();
        v2.start();
        v3.start();
        v1.join();
        v2.join();
        v3.join();
        System.out.println(count);
    }

    
}
