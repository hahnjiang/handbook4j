package org.hahn.learning.guava;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;

/**
 * Created by jianghan on 2017/3/20.
 */
public class ConvertGet implements AsyncFunction<String, String> {
    @Override
    public ListenableFuture<String> apply(String input) throws Exception {
        SettableFuture<String> future = SettableFuture.create();
        future.set(input + "=>output");
        return future;
    }
}
