package org.hahn.learning.guava;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * Created by jianghan on 2017/3/20.
 */
public class ConvertGetTask implements Callable<Long> {

    private CountDownLatch end;

    public ConvertGetTask(CountDownLatch end) {
        this.end = end;
    }

    @Override
    public Long call() throws Exception {
        Thread.sleep(5);
        return System.currentTimeMillis();
    }

}
