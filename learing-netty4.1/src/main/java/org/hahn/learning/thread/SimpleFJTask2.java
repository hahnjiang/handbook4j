package org.hahn.learning.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * Created by jianghan on 16-7-12.
 */
public class SimpleFJTask2 extends RecursiveTask<Long> {

    private int count;

    public SimpleFJTask2(int count) {
        this.count = count;
    }

    @Override
    protected Long compute() {
        long start = System.currentTimeMillis(), end;
        if (count > 1) {
            List<SimpleFJTask2> l = new ArrayList<>(count);
            for (int i = 0; i < count; ++i) {
                l.add(new SimpleFJTask2(1));
            }
            invokeAll(l);
        } else {
            long prime = 1073676287L;
            for (int i = 2; i * i < prime; ++i) {
                if (prime % i == 0) break;
            }
        }
        end = System.currentTimeMillis();
        return end - start;
    }

}
