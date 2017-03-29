package org.hahn.learning.guava;

import com.google.common.util.concurrent.FutureCallback;

import java.util.concurrent.CountDownLatch;

/**
 * Created by jianghan on 2017/3/20.
 */
public class FCallback implements FutureCallback<Long> {
    private CountDownLatch end;

    public FCallback(CountDownLatch end) {
        this.end = end;
    }

    @Override
    public void onSuccess(Long result) {
        end.countDown();
    }

    @Override
    public void onFailure(Throwable t) {
        t.printStackTrace();
    }
}
