package com.cmy.basis.thread.p;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadTest {

    public static void main(String[] args) throws Exception {
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < 1000; i++)
            queue.offer(i);

        long st = System.currentTimeMillis();
        single(queue);
//        cache(queue);
//        cache(queue);
//        call(queue);
//        run(queue);
//        group(queue);
//        start(queue);
        System.out.println(System.currentTimeMillis() - st);
    }
    static int threadNum = 100;

    public static void single(ConcurrentLinkedQueue<Integer> queue) {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        Caller caller = new Caller(queue);
        
        ArrayList<Future<ResultParam>> futures = new ArrayList<>();
        for (int i = 0; i < threadNum; i++) {
            futures.add(pool.submit(caller));
        }
        try {
            for (int i = 0; i < threadNum; i++) {
                ResultParam rs = futures.get(i).get();
                System.out.println(rs.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        pool.shutdown();
    }
    public static void schedule(ConcurrentLinkedQueue<Integer> queue) {
        ExecutorService pool = Executors.newScheduledThreadPool(
                10, null);
        Caller caller = new Caller(queue);
        
        ArrayList<Future<ResultParam>> futures = new ArrayList<>();
        for (int i = 0; i < threadNum; i++) {
            futures.add(pool.submit(caller));
        }
        try {
            for (int i = 0; i < threadNum; i++) {
                ResultParam rs = futures.get(i).get();
                System.out.println(rs.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        pool.shutdown();
    }
    public static void cache(ConcurrentLinkedQueue<Integer> queue) {
        ExecutorService pool = Executors.newCachedThreadPool();
        Caller caller = new Caller(queue);
        
        ArrayList<Future<ResultParam>> futures = new ArrayList<>();
        for (int i = 0; i < threadNum; i++) {
            futures.add(pool.submit(caller));
        }
        try {
            for (int i = 0; i < threadNum; i++) {
                ResultParam rs = futures.get(i).get();
                System.out.println(rs.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        pool.shutdown();
    }
    
    public static void call(ConcurrentLinkedQueue<Integer> queue) {
        Caller caller = new Caller(queue);
        ExecutorService es = Executors.newFixedThreadPool(threadNum);
        
        ArrayList<Future<ResultParam>> futures = new ArrayList<>();
        for (int i = 0; i < threadNum; i++) {
            futures.add(es.submit(caller));
        }
        try {
            for (int i = 0; i < threadNum; i++) {
                ResultParam rs = futures.get(i).get();
                System.out.println(rs.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        es.shutdown();
    }

    public static void run(ConcurrentLinkedQueue<Integer> queue) {
        Runner runner = new Runner(queue);
        ExecutorService pool = Executors.newFixedThreadPool(threadNum);
        for (int i = 0; i < threadNum; i++) {
            pool.execute(runner);
        }
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void group(ConcurrentLinkedQueue<Integer> queue) {
        Runner runner = new Runner(queue);
        ThreadGroup group = new ThreadGroup("g");
        for (int i = 0; i < threadNum; i++) {
            Thread t = new Thread(group, runner);
            t.start();
        }

        while (true) {
            if (group.activeCount() == 0) {
                break;
            }
        }
    }
    
    public static void start(ConcurrentLinkedQueue<Integer> queue) {
        Runner runner = new Runner(queue);
        Vector<Thread> v = new Vector<>();
        for (int i = 0; i < threadNum; i++) {
            Thread t = new Thread(runner);
            v.add(t);
            t.start();
        }
        try {
            for (Thread t : v) {
                t.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("done");
    }
    
    
}
