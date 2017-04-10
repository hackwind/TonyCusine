package com.tonyhu.cookbook.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerBottomHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerTopHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationMainContentHandler;
import com.tonyhu.cookbook.db.Motto;
import com.tonyhu.cookbook.db.MottoDao;
import com.tonyhu.cookbook.fragment.MainFragment;
import com.tonyhu.cookbook.R;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends com.blunderer.materialdesignlibrary.activities.NavigationDrawerActivity {

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
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int year = cal.get(Calendar.DAY_OF_YEAR);
        MottoDao dao = new MottoDao();
        List<Motto> list = dao.listAll();
        int size = list.size();
        String motto = list.get(year % size).getMotto();
        return new NavigationDrawerTopHandler()
                .addSection(getTip(hour),motto);

    }

    @Override
    protected NavigationDrawerBottomHandler getNavigationDrawerBottomHandler() {
        return new NavigationDrawerBottomHandler()
                .addItem(R.string.drawer_favorite,0,mOnClickListener)
                .addItem(R.string.drawer_recommend,0,mOnClickListener)
                .addItem(R.string.drawer_feedback,0,mOnClickListener)
                .addItem(R.string.drawer_update,0,mOnClickListener)
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

            } else if(title.equals(getString(R.string.drawer_favorite))) {

            } else if(title.equals(getString(R.string.drawer_update))) {

            } else if(title.equals(getString(R.string.drawer_about))) {
                startActivity(AboutActivity.class);
            }
        }
    };

    private void startActivity(Class cls) {
        Intent intent = new Intent(this,cls);
        startActivity(intent);
    }

    private String getTip(int hour) {
        if(hour > 20) {
            return "主人，晚上好";
        } else if(hour > 18) {
            return "主人，晚上好，记得吃晚饭哦";
        } else if(hour > 12) {
            return "主人，下午好";
        } else if(hour > 10) {
            return "主人，中午好，记得吃午饭哦";
        } else if(hour > 8) {
            return "主人，上午好";
        } else if(hour > 5) {
            return "主人，早上好，记得吃早餐哦";
        } else {
            return "主人，凌晨好";
        }
    }
}
