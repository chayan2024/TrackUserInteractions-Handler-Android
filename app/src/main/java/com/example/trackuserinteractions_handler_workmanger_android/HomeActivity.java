package com.example.trackuserinteractions_handler_workmanger_android;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;

import com.example.trackuserinteractions_handler_workmanger_android.util.AutoNavigateWorker;
import com.example.trackuserinteractions_handler_workmanger_android.util.WorkManagerHelper;

import java.util.concurrent.TimeUnit;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WorkManagerHelper.getInstance(this).scheduleTask(AutoNavigateWorker.class, 1, TimeUnit.MINUTES);

        // Your home activity initialization code here
        // This is where you can initialize UI components, set listeners, etc.
    }
}

