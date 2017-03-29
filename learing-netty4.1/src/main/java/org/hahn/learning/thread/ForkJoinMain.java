package org.hahn.learning.thread;

import org.apache.commons.lang3.RandomUtils;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by jianghan on 16/6/17.
 */
public class ForkJoinMain {
    public static void main(String[] args) throws Exception {
        List<String> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
        }
        Iterator<String> itor = list.iterator();
        String cur = itor.next();
        String next = itor.next();
        itor.remove();
        for (String i : list) {
            System.out.println(i);
        }
        long[] array = new long[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = RandomUtils.nextLong(0, 1000);
        }
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask sort = new SortTask(array);
        pool.submit(sort);
        pool.shutdown();

        pool.awaitTermination(30, TimeUnit.SECONDS);
        for (long l : array) {
            // System.out.println(l);
        }

        ForkJoinTask<Integer> fjt = new Fibonacci(45);
        ForkJoinPool fjpool = new ForkJoinPool();
        Future<Integer> result = fjpool.submit(fjt);

        // do something
        System.out.println(result.get());
    }
}
