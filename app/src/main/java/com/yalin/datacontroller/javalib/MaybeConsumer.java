package com.yalin.datacontroller.javalib;

/**
 * 作者：YaLin
 * 日期：2016/10/26.
 */

public interface MaybeConsumer<T> extends FailureListener {
    void success(T value);
}
