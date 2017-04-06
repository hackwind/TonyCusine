package com.tonyhu.cookbook.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.blunderer.materialdesignlibrary.adapters.ViewPagerWithTabsAdapter;
import com.blunderer.materialdesignlibrary.handlers.ViewPagerHandler;
import com.blunderer.materialdesignlibrary.models.ViewPagerItem;
import com.tonyhu.cookbook.R;
import com.tonyhu.cookbook.db.Category;
import com.tonyhu.cookbook.db.CategoryDao;
import com.tonyhu.cookbook.fragment.FoodCuisineFragment;
import com.tonyhu.cookbook.pageslidinglibrary.PagerSlidingTabStrip;

import java.util.List;

import static com.tonyhu.cookbook.TonyApplication.getContext;


/*Created by Administrator on 2017/4/5.
 */

public class FoodSubTypeActivity extends BaseActivity {
    private int category;
    private String categoryName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cuisine);

        category = getIntent().getIntExtra("category",0);
        categoryName = getIntent().getStringExtra("category_name");

        initView();
    }

    private void initView() {
        List<ViewPagerItem> viewPagerItems = getViewPagerItems();

        if (viewPagerItems != null && viewPagerItems.size() > 0) {
            ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
            pager.setAdapter(new ViewPagerWithTabsAdapter(getContext(), getSupportFragmentManager(), viewPagerItems));
            pager.setCurrentItem(0);

            PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
            tabs.setTextColor(getResources().getColor(android.R.color.white));
            tabs.setViewPager(pager);
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
                tabs.setTabBackground(android.R.attr.selectableItemBackground);
        }
    }

    private List<ViewPagerItem> getViewPagerItems(){
        List<Category> categories = new CategoryDao().listByParentCategory(category);
        if(categories == null) {
            return null;
        }
        ViewPagerHandler handler = new ViewPagerHandler();
        for(Category category : categories) {
            handler.addPage(category.getName(), FoodCuisineFragment.newInstance(category.getCategory(), category.getName()));
        }
        return handler.getViewPagerItems();
    }

}
