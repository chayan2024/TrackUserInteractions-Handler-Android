package com.example.trackuserinteractions_handler_workmanger_android;

import android.os.Bundle;
        import android.os.Handler;
        import android.os.Looper;
        import android.view.MotionEvent;
        import android.widget.Toast;
        import androidx.appcompat.app.AppCompatActivity;

public class SDS extends AppCompatActivity {

    // Declaring handler, runnable and time in milliseconds
    private Handler mHandler;
    private Runnable mRunnable;
    private long mTime = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing the handler and the runnable
        mHandler = new Handler(Looper.getMainLooper());
        mRunnable = new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "User inactive for " + (mTime / 1000) + " secs!", Toast.LENGTH_SHORT).show();
            }
        };

        // Start the handler
        startHandler();
    }

    // When the screen is touched or motion is detected
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Removes the handler callbacks (if any)
        stopHandler();

        // Runs the handler (for the specified time)
        // If any touch or motion is detected before
        // the specified time, this override function is again called
        startHandler();

        return super.onTouchEvent(event);
    }

    // start handler function
    private void startHandler() {
        mHandler.postDelayed(mRunnable, mTime);
    }

    // stop handler function
    private void stopHandler() {
        mHandler.removeCallbacks(mRunnable);
    }
}
