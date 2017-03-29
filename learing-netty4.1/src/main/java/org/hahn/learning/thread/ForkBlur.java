package org.hahn.learning.thread;

import java.util.concurrent.RecursiveTask;

/**
 * Created by jianghan on 16/6/17.
 */
public class ForkBlur extends RecursiveTask<Long> {
    @Override
    protected Long compute() {
        return 10L;
    }
}
