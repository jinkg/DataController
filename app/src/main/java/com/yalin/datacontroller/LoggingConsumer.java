package com.yalin.datacontroller;

import com.yalin.datacontroller.javalib.MaybeConsumer;
import com.yalin.datacontroller.log.StatLog;

/**
 * 作者：YaLin
 * 日期：2016/10/26.
 */

public abstract class LoggingConsumer<T> implements MaybeConsumer<T> {
    private final String mTag;
    private final String mOperation;

    public LoggingConsumer(String tag, String operation) {
        mTag = tag;
        mOperation = operation;
    }

    @Override
    public void fail(Exception e) {
        StatLog.printLog(mTag, "Failed: " + mOperation, e);
    }
}
