package com.tonyhu.cookbook.activity;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.blunderer.materialdesignlibrary.handlers.ViewPagerHandler;
import com.tonyhu.cookbook.R;
import com.tonyhu.cookbook.fragment.WelcomeFragment;
import com.tonyhu.cookbook.util.PreferenceUtil;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstance) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_welcome);
        startTimer();
    }

    private void startTimer() {
        try {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                  Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                  startActivity(intent);
                    finish();
                }
            },2000);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
