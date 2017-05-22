package com.tonyhu.cookbook.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerBottomHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerTopHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationMainContentHandler;
import com.qihoo.appstore.common.updatesdk.lib.UpdateHelper;
import com.tonyhu.cookbook.fragment.MainFragment;
import com.tonyhu.cookbook.R;


public class MainActivity extends com.blunderer.materialdesignlibrary.activities.NavigationDrawerActivity {
    private long lastOnBackPressed = 0;
    private int backPressCount = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initStatusBar(R.color.color_primary);
        //调用360检查更新sdk，后面的颜色是升级对话框状态栏颜色
        UpdateHelper.getInstance().init(getApplicationContext(), Color.parseColor("#0A93DB"));
    }

    @Override
    protected View.OnClickListener getMenuItemClickListener(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(SearchActivity.class);
            }
        };
    }

    @Override
    protected NavigationDrawerTopHandler getNavigationDrawerTopHandler() {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(new Date());
//        int hour = cal.get(Calendar.HOUR_OF_DAY);
//        int year = cal.get(Calendar.DAY_OF_YEAR);
//        MottoDao dao = new MottoDao();
//        List<Motto> list = dao.listAll();
//        int size = list.size();
//        String motto = list.get(year % size).getMotto();
//        return new NavigationDrawerTopHandler()
//                .addSection(getTip(hour),motto);
          return null;
    }

    @Override
    protected NavigationDrawerBottomHandler getNavigationDrawerBottomHandler() {
        return new NavigationDrawerBottomHandler()
                .addItem(R.string.drawer_favorite,0,mOnClickListener)
                .addItem(R.string.drawer_feedback,0,mOnClickListener)
                .addItem(R.string.drawer_update,0,mOnClickListener)
                .addItem(R.string.drawer_reward,0,mOnClickListener)
                .addItem(R.string.drawer_about,0,mOnClickListener);
    }

    @Override
    protected NavigationMainContentHandler getNavigationMainContentHandler() {
        return new NavigationMainContentHandler().setContent(MainFragment.newInstance());
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            TextView tvTitle = (TextView) view.findViewById(R.id.navigation_drawer_row_title);
            if(tvTitle == null) return;
            CharSequence title = tvTitle.getText();
            if(title.equals(getString(R.string.drawer_favorite))) {
                startActivity(MyFavoriteActivity.class);
            } else if(title.equals(getString(R.string.drawer_feedback))) {
                startActivity(FeedBackActivity.class);
            } else if(title.equals(getString(R.string.drawer_reward))) {
                startActivity(RewardActivity.class);
            } else if(title.equals(getString(R.string.drawer_update))) {
                UpdateHelper.getInstance().manualUpdate(getPackageName());
            } else if(title.equals(getString(R.string.drawer_about))) {
                startActivity(AboutActivity.class);
            }
        }
    };

    private void startActivity(Class cls) {
        Intent intent = new Intent(this,cls);
        startActivity(intent);
    }

    public void openDrawer(){
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if(backPressCount == 0) {
                backPressCount ++;
                lastOnBackPressed = System.currentTimeMillis();
                Toast.makeText(this,R.string.press_back_again,Toast.LENGTH_SHORT).show();
                return;
            } else if(System.currentTimeMillis() - lastOnBackPressed > 3000) {
                lastOnBackPressed = System.currentTimeMillis();
                backPressCount = 0;
                Toast.makeText(this,R.string.press_back_again,Toast.LENGTH_LONG).show();
                return;
            }
            super.onBackPressed();
        }
    }

    /**
     * 状态栏处理：解决从欢迎页全屏切换到本页非全屏页面被压缩导致抖动一下问题
     */
    public void initStatusBar(int barColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            // 获取状态栏高度
            int statusBarHeight = getResources().getDimensionPixelSize(resourceId);
            View rectView = new View(this);
            // 绘制一个和状态栏一样高的矩形，并添加到视图中
            LinearLayout.LayoutParams params
                    = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
            rectView.setLayoutParams(params);
            //设置状态栏颜色
            rectView.setBackgroundColor(getResources().getColor(barColor));
            // 添加矩形View到布局中
            ViewGroup decorView = (ViewGroup) getWindow().getDecorView();
            decorView.addView(rectView);
            ViewGroup rootView = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(true);
        }
    }

//    private String getTip(int hour) {
//        if(hour > 20) {
//            return "主人，晚上好";
//        } else if(hour > 18) {
//            return "主人，晚上好，记得吃晚饭哦";
//        } else if(hour > 12) {
//            return "主人，下午好";
//        } else if(hour > 10) {
//            return "主人，中午好，记得吃午饭哦";
//        } else if(hour > 8) {
//            return "主人，上午好";
//        } else if(hour > 5) {
//            return "主人，早上好，记得吃早餐哦";
//        } else {
//            return "主人，凌晨好";
//        }
//    }
}
