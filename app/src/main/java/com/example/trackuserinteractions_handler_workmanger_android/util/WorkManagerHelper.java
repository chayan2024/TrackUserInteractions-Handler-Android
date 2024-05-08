package com.example.trackuserinteractions_handler_workmanger_android.util;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;

import java.util.concurrent.TimeUnit;

public class WorkManagerHelper {

    private static WorkManagerHelper instance;
    private WorkManager workManager;

    private WorkManagerHelper(Context context) {
        workManager = WorkManager.getInstance(context);
    }

    public static synchronized WorkManagerHelper getInstance(Context context) {
        if (instance == null) {
            instance = new WorkManagerHelper(context.getApplicationContext());
        }
        return instance;
    }

    public void scheduleTask(Class<? extends Worker> workerClass, long delay, TimeUnit timeUnit) {
        Constraints constraints = new Constraints.Builder()
                .setRequiresCharging(false)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(workerClass)
                .setInitialDelay(delay, timeUnit)
                .setConstraints(constraints)
                .build();

        workManager.enqueue(workRequest);
    }

    public void cancelAllTasks() {
        workManager.cancelAllWork();
    }
}

