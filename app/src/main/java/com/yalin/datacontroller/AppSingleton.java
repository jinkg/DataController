package com.yalin.datacontroller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.yalin.datacontroller.javalib.FailureListener;
import com.yalin.datacontroller.metadata.SimpleMetaDataManager;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 作者：YaLin
 * 日期：2016/10/26.
 */

public class AppSingleton {
    @SuppressLint("StaticFieldLeak")
    private static AppSingleton sInstance;
    private final Context mApplicationContext;
    private DataController mDataController;

    private static Executor sUiThreadExecutor = null;
    private static UserDataController mUserDataController;

    public DataController getDataController() {
        if (mDataController == null) {
            mDataController = new DataControllerImpl(
                    getUiThreadExecutor(), Executors.newSingleThreadExecutor(),
                    new SimpleMetaDataManager(mApplicationContext)
            );
        }
        return mDataController;
    }

    public static Executor getUiThreadExecutor() {
        if (sUiThreadExecutor == null) {
            final Handler handler = new Handler(Looper.getMainLooper());
            sUiThreadExecutor = new Executor() {
                @Override
                public void execute(@NonNull Runnable command) {
                    handler.post(command);
                }
            };
        }
        return sUiThreadExecutor;
    }

    public static AppSingleton getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new AppSingleton(context);
        }
        return sInstance;
    }

    public UserDataController getUserDataController() {
        if (mUserDataController == null) {
            mUserDataController = new UserDataController(getDataController(),
                    new UserDataController.FailureListenerFactory() {
                        @Override
                        public FailureListener makeListenerForOperation(String operation) {
                            return LoggingConsumer.logFailure(operation);
                        }
                    });
        }
        return mUserDataController;
    }

    private AppSingleton(Context context) {
        mApplicationContext = context.getApplicationContext();
    }
}
