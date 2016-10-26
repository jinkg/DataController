package com.yalin.datacontroller;

import com.yalin.datacontroller.javalib.MaybeConsumer;
import com.yalin.datacontroller.javalib.Success;
import com.yalin.datacontroller.metadata.User;

import java.util.List;

/**
 * 作者：YaLin
 * 日期：2016/10/26.
 */

public interface DataController {
    void addUser(User user, MaybeConsumer<User> onSuccess);

    void readUsers(MaybeConsumer<List<User>> onSuccess);

    void updateUser(User newUser, MaybeConsumer<Success> onSuccess);

    void updateUserName(String currentName,String newName,MaybeConsumer<Success> onSuccess);
}
