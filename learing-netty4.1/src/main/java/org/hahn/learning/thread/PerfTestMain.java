package org.hahn.learning.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * Created by jianghan on 16/7/7.
 */
public class PerfTestMain {

    public static void main(String[] args) throws Exception {
        // int taskNum = Integer.parseInt(args[0]);
        int taskNum = 4000;
        long start, end, sum = 0;
        List<Future<Long>> futures = new ArrayList<>(taskNum);
        // ExecutorService esPool = Executors.newCachedThreadPool();
        ExecutorService esPool = Executors.newFixedThreadPool(5);
        ForkJoinPool fjPool = new ForkJoinPool(5);

        start = System.currentTimeMillis();
        SimpleFJTask fj;
        for (int i = 0; i < taskNum; ++i) {
            fj = new SimpleFJTask();
            futures.add(fjPool.submit(fj));
        }
        for (Future<Long> f : futures) {
            long b = f.get();
            sum += b;
        }
        end = System.currentTimeMillis();
        System.out.println("ForkJoin all Time: " + (end - start));
        System.out.println("ForkJoin Per Time: " + (double) (sum) / taskNum);

        sum = 0;
        start = System.currentTimeMillis();
        SimpleFJTask2 fj2;
        for (int i = 0; i < taskNum; ++i) {
            fj2 = new SimpleFJTask2(25);
            futures.add(fjPool.submit(fj2));
        }
        for (Future<Long> f : futures) {
            long b = f.get();
            sum += b;
        }
        end = System.currentTimeMillis();
        System.out.println("ForkJoin all Time: " + (end - start));
        System.out.println("ForkJoin Per Time: " + (double) (sum) / taskNum);

        sum = 0;
        futures = new ArrayList<>(taskNum);
        start = System.currentTimeMillis();
        SimpleESTask es;
        for (int i = 0; i < taskNum; ++i) {
            es = new SimpleESTask();
            futures.add(esPool.submit(es));
        }
        for (Future<Long> f : futures) {
            long b = f.get();
            sum += b;
        }
        end = System.currentTimeMillis();
        System.out.println("Executor all Time: " + (end - start));
        System.out.println("Executor Per Time: " + (double) (sum) / taskNum);

        sum = 0;
        futures = new ArrayList<>(taskNum * 25);
        start = System.currentTimeMillis();
        SimpleESTask2 es2;
        for (int i = 0; i < taskNum * 25; ++i) {
            es2 = new SimpleESTask2();
            futures.add(esPool.submit(es2));
        }
        for (Future<Long> f : futures) {
            long b = f.get();
            sum += b;
        }
        end = System.currentTimeMillis();
        System.out.println("Executor all Time: " + (end - start));
        System.out.println("Executor Per Time: " + (double) (sum) / taskNum);

        esPool.shutdown();
        fjPool.shutdown();
    }

}

/**
 * ForkJoin all Time: 3545
 * ForkJoin Per Time: 0.88625
 * Executor all Time: 3163
 * Executor Per Time: 0.79075
 * Executor all Time: 3053
 * Executor Per Time: 0.76325
 */