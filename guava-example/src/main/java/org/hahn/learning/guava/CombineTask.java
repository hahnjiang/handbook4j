package org.hahn.learning.guava;

import java.util.concurrent.Callable;

/**
 * Created by jianghan on 2017/3/20.
 */
public class CombineTask implements Callable<Long> {

    @Override
    public Long call() throws Exception {
        Thread.sleep(5);
        System.out.println("CombineTask");
        return System.currentTimeMillis();
    }

}
