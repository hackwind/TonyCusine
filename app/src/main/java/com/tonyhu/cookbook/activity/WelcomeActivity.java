package com.tonyhu.cookbook.activity;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import com.blunderer.materialdesignlibrary.handlers.ViewPagerHandler;
import com.tonyhu.cookbook.R;
import com.tonyhu.cookbook.fragment.WelcomeFragment;
import com.tonyhu.cookbook.util.PreferenceUtil;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends BaseActivity {
    private final static int DURATION = 2000;
    private Handler handler;
    private TextView text1;
    private TextView text2;

    @Override
    public void onCreate(Bundle savedInstance) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_welcome);
        startTimer();
    }

    private void startTimer() {
        AlphaAnimation aa = new AlphaAnimation(0.0f,1.0f);
        aa.setDuration(DURATION);
        findViewById(R.id.text1).startAnimation(aa);
        findViewById(R.id.text2).startAnimation(aa);

        handler = new Handler();
        try {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                  Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                  startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    finish();
                }
            },DURATION);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
