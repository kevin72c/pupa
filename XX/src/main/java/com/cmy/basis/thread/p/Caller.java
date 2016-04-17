package com.cmy.basis.thread.p;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Caller implements Callable<ResultParam>{

    @Override
    public ResultParam call() throws Exception {
        while (true) {
            Integer poll = queue.poll();
            if (poll == null)
                return new ResultParam(100, "done");
            System.out.println(Thread.currentThread().getName() + ":" + poll);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private ConcurrentLinkedQueue<Integer> queue;

    public Caller(ConcurrentLinkedQueue<Integer> queue) {
        this.queue = queue;
    }
}
