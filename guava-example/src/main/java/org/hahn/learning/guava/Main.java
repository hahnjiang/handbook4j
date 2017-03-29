package org.hahn.learning.guava;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jianghan on 2017/3/20.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(16));
        ExecutorService js = Executors.newFixedThreadPool(4);
        for (; ; ) {
            int COUNT = 4 * 200;
            CountDownLatch end = new CountDownLatch(COUNT);

            for (int i = 0; i < COUNT; i++) {
                ConvertGetTask task = new ConvertGetTask(end);
                FCallback callback = new FCallback(end);
                ListenableFuture future = service.submit(task);
                Futures.addCallback(future, callback);
            }
            long st = System.currentTimeMillis();
            System.out.println("Start");
            end.await();
            long en = System.currentTimeMillis();
            System.out.println("End");
            System.out.println(en - st);
        }
    }
}
