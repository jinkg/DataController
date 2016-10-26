package com.yalin.datacontroller.metadata;

import java.util.List;

/**
 * 作者：YaLin
 * 日期：2016/10/26.
 */

public interface MetaDataManager {
    long addUser(User user);

    List<User> readUsers();

    void updateUser(User newUser);

    void close();
}
