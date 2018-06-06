package com.example.zilvinastomkevicius.nostarve4u.Activities;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.example.zilvinastomkevicius.nostarve4u.R;

import maes.tech.intentanim.CustomIntent;

public class LaunchingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launching);

        final Intent intent = new Intent(this, LoginActivity.class);

        new CountDownTimer(4000, 1000)
        {
            public void onTick(long milisec)
            {

            }

            public void onFinish()
            {
                finish();

                startActivity(intent);
                CustomIntent.customType(LaunchingActivity.this, "fadein-to-fadeout");
            }
        }.start();
    }
}
