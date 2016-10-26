package com.yalin.datacontroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.yalin.datacontroller.metadata.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addUser(View view) {
        User user = new User();
        user.name = "yalin1";
        user.age = 26;

        AppSingleton.getInstance(this).getDataController().addUser(user,
                new LoggingConsumer<User>(TAG, "add user") {
                    @Override
                    public void success(User value) {
                        Log.d(TAG, "add user success: " + value.toString());
                    }
                });
    }

    public void readUser(View view) {
        AppSingleton.getInstance(this).getDataController().readUsers(
                new LoggingConsumer<List<User>>(TAG, "read user") {
                    @Override
                    public void success(List<User> value) {
                        Log.d(TAG, "read user success: " + value);
                    }
                }
        );
    }
}
