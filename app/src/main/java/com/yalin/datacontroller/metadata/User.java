package com.yalin.datacontroller.metadata;

/**
 * 作者：YaLin
 * 日期：2016/10/26.
 */

public class User {
    public long id;
    public String name;
    public int age;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
