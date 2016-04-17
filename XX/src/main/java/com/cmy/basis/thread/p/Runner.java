package com.cmy.basis.thread.p;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Runner implements Runnable {

    private ConcurrentLinkedQueue<Integer> queue;

    public Runner(ConcurrentLinkedQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            Integer poll = queue.poll();
            if (poll == null)
                break;
            System.out.println(Thread.currentThread().getName() + ":" + poll);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
