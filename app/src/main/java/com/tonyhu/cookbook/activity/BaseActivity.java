package com.tonyhu.cookbook.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.tonyhu.cookbook.util.SystemStatusManager;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by Administrator on 2017/3/29.
 */

public class BaseActivity extends SwipeBackActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranslucentStatus();
        getSwipeBackLayout().setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }

    //设置系统状态栏
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setTranslucentStatus()
    {
        //判断版本是4.4以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);

            SystemStatusManager tintManager = new SystemStatusManager(this);
            //打开系统状态栏控制
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarAlpha(0.5f);
        }
    }
}
