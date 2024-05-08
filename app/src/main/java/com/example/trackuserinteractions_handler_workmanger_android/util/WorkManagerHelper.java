package com.example.trackuserinteractions_handler_workmanger_android.util;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;

import java.util.concurrent.TimeUnit;
import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import java.util.concurrent.TimeUnit;

public class WorkManagerHelper {

    private static WorkManagerHelper instance;
    private WorkManager workManager;
    private Context appContext;

    private WorkManagerHelper(Context context) {
        appContext = context.getApplicationContext();
        workManager = WorkManager.getInstance(appContext);
    }

    public static synchronized WorkManagerHelper getInstance(Context context) {
        if (instance == null) {
            instance = new WorkManagerHelper(context);
        }
        return instance;
    }

    public void scheduleTask(long delay, TimeUnit timeUnit, Runnable task) {
        Constraints constraints = new Constraints.Builder()
                .setRequiresCharging(false)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(AutoNavigateWorker.class)
                .setInitialDelay(delay, timeUnit)
                .setConstraints(constraints)
                .build();


        workManager.enqueue(workRequest);
    }

    public void cancelAllTasks() {
        workManager.cancelAllWork();
    }
}


