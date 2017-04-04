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

public class WelcomeActivity extends com.blunderer.materialdesignlibrary.activities.ViewPagerActivity {

    @Override
    public void onCreate(Bundle savedInstance) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstance);
        firstLogin();
    }

    @Override
    protected ViewPagerHandler getViewPagerHandler() {
        return new ViewPagerHandler()
                .addPage(R.string.section1, WelcomeFragment.newInstance(R.drawable.welcome1,false))
                .addPage(R.string.section1, WelcomeFragment.newInstance(R.drawable.welcome2,true));
    }

    @Override
    protected boolean showViewPagerIndicator() {
        return true;
    }

    @Override
    protected boolean replaceActionBarTitleByViewPagerItemTitle() {
        return true;
    }

    @Override
    protected int defaultViewPagerItemSelectedPosition() {
        return 0;
    }

    @Override
    protected boolean hideToolbar() {
        return true;
    }

    private void firstLogin() {
        try {
            PackageInfo info = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
            String newVersion = info.versionName;
            String oldVersion = PreferenceUtil.getString("version");
            if(newVersion.equals(oldVersion)) {
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                PreferenceUtil.putString("version",newVersion);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
