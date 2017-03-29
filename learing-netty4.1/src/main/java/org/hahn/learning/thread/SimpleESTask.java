package org.hahn.learning.thread;

import java.util.concurrent.Callable;

/**
 * Created by jianghan on 16/7/7.
 */
public class SimpleESTask implements Callable<Long> {
    @Override
    public Long call() {
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
