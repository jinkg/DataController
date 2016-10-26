package com.yalin.datacontroller.javalib;

/**
 * 作者：YaLin
 * 日期：2016/10/26.
 */

public class MaybeConsumers {
    public static <T> MaybeConsumer<T> chainFailure(final FailureListener failure,
                                                    final Consumer<T> success) {
        return new MaybeConsumer<T>() {
            @Override
            public void success(T value) {
                success.take(value);
            }

            @Override
            public void fail(Exception e) {
                failure.fail(e);
            }
        };
    }
}
