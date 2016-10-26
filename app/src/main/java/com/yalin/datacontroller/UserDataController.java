package com.yalin.datacontroller;

import android.util.Log;

import com.yalin.datacontroller.javalib.Consumer;
import com.yalin.datacontroller.javalib.FailureListener;
import com.yalin.datacontroller.javalib.MaybeConsumer;
import com.yalin.datacontroller.javalib.MaybeConsumers;
import com.yalin.datacontroller.javalib.Success;

/**
 * 作者：YaLin
 * 日期：2016/10/26.
 */

public class UserDataController {
    private static final String TAG = "UserDataController";

    private final DataController mDataController;
    private FailureListenerFactory mListenerFactory;

    public UserDataController(DataController dataController, FailureListenerFactory listenerFactory) {
        mDataController = dataController;
        mListenerFactory = listenerFactory;
    }

    public void renameUser(String currentName, String newName) {
        getDataController().updateUserName(currentName, newName,
                doOrReportFailure("change user name.",
                        new Consumer<Success>() {
                            @Override
                            public void take(Success success) {
                                Log.d(TAG, "rename user success!");
                            }
                        }));
    }

    DataController getDataController() {
        return mDataController;
    }

    <T> MaybeConsumer<T> doOrReportFailure(String operation, Consumer<T> onSuccess) {
        return MaybeConsumers.chainFailure(mListenerFactory.makeListenerForOperation(operation),
                onSuccess);
    }

    public interface FailureListenerFactory {
        FailureListener makeListenerForOperation(String operation);
    }
}
