package com.example.trackuserinteractions_handler_workmanger_android.util;

// AutoNavigateWorker.java
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.trackuserinteractions_handler_workmanger_android.DetailsActivity;

public class AutoNavigateWorker extends Worker {

    public AutoNavigateWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        // Perform the task here, for example, navigating to a screen
        //navigateToDesiredScreen();
        return Result.success();
    }

    private void navigateToDesiredScreen() {
        // Implement the code to navigate to the desired screen here
        // For example:
         Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
         intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
         getApplicationContext().startActivity(intent);
    }
}

