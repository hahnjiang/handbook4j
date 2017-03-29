package org.hahn.learning.thread;

import java.util.concurrent.RecursiveTask;

/**
 * Created by jianghan on 16/7/7.
 */
public class SimpleFJTask extends RecursiveTask<Long> {
    @Override
    protected Long compute() {
        long start = System.currentTimeMillis(), end;
        long prime = 1073676287L;
        for (int idx = 0; idx < 25; ++idx) {
            for (int i = 2; i * i < prime; ++i) {
                if (prime % i == 0) break;
            }
        }
        end = System.currentTimeMillis();
        return end - start;
    }
}
