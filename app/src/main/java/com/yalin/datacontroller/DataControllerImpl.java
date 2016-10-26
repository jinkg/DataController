package com.yalin.datacontroller;

import com.yalin.datacontroller.javalib.MaybeConsumer;
import com.yalin.datacontroller.metadata.MetaDataManager;
import com.yalin.datacontroller.metadata.User;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/**
 * 作者：YaLin
 * 日期：2016/10/26.
 */

public class DataControllerImpl implements DataController {
    private final Executor mUiThread;
    private final Executor mMetaDataThread;
    private MetaDataManager mMetaDataManager;

    public DataControllerImpl(Executor uiThread, Executor metaDataThread, MetaDataManager metaDataManager) {
        mUiThread = uiThread;
        mMetaDataThread = metaDataThread;
        mMetaDataManager = metaDataManager;
    }

    @Override
    public void addUser(final User user, MaybeConsumer<User> onSuccess) {
        background(mMetaDataThread, onSuccess, new Callable<User>() {
            @Override
            public User call() throws Exception {
                user.id = mMetaDataManager.addUser(user);
                return user;
            }
        });
    }

    @Override
    public void readUsers(MaybeConsumer<List<User>> onSuccess) {
        background(mMetaDataThread, onSuccess, new Callable<List<User>>() {
            @Override
            public List<User> call() throws Exception {
                return mMetaDataManager.readUsers();
            }
        });
    }

    private <T> void background(Executor dataThread, final MaybeConsumer<T> onSuccess,
                                final Callable<T> job) {
        dataThread.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    final T result = job.call();
                    mUiThread.execute(new Runnable() {
                        @Override
                        public void run() {
                            onSuccess.success(result);
                        }
                    });
                } catch (final Exception e) {
                    mUiThread.execute(new Runnable() {
                        @Override
                        public void run() {
                            onSuccess.fail(e);
                        }
                    });
                }
            }
        });
    }
}
