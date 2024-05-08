package com.example.trackuserinteractions_handler_workmanger_android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    private static final long inactivityDelay = 30000; // 30seconds of inactivity
    private Handler inactivityHandler;
    private Runnable inactivityRunnable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Setup inactivity handler
        inactivityHandler = new Handler();
        inactivityRunnable = () -> {
            // Broadcast intent to navigate back to home screen on inactivity
            sendBroadcast(new Intent("ACTION_USER_INACTIVE"));
        };

        // Set up custom TouchListener
        findViewById(android.R.id.content).setOnTouchListener((v, event) -> {
            resetInactivityTimer();
            return false;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        startInactivityTimer();
        registerReceiver(inactivityReceiver, new IntentFilter("ACTION_USER_INACTIVE"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopInactivityTimer();
        unregisterReceiver(inactivityReceiver);
    }

    private void startInactivityTimer() {
        inactivityHandler.postDelayed(inactivityRunnable, inactivityDelay);
    }

    private void stopInactivityTimer() {
        inactivityHandler.removeCallbacks(inactivityRunnable);
    }

    // Custom TouchListener to detect user interactions
    private final View.OnTouchListener customTouchListener = (v, event) -> {
        resetInactivityTimer();
        return false;
    };

    private void resetInactivityTimer() {
        stopInactivityTimer();
        startInactivityTimer();
    }

    // Broadcast Receiver to handle inactivity intent
    private final BroadcastReceiver inactivityReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null && intent.getAction().equals("ACTION_USER_INACTIVE")) {
                // Handle the intent to navigate back to home screen
                Intent homeIntent = new Intent(context, HomeActivity.class);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(homeIntent);
            }
        }
    };
}

