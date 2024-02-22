package com.kawser.cprf;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {
    private Timer _timer = new Timer();


    //private ImageView imageview1;

    private Intent in = new Intent();
    private TimerTask i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        initializeLogic();
    }


    private void initializeLogic() {
        i = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        in.setClass(getApplicationContext(), dashboard.class);
                        startActivity(in);
                        finish();
                    }
                });
            }
        };
        _timer.schedule(i, (int) (2000));
    }
}