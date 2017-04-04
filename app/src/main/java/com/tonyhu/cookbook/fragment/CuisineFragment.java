package com.tonyhu.cookbook.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blunderer.materialdesignlibrary.adapters.ViewPagerWithTabsAdapter;
import com.blunderer.materialdesignlibrary.handlers.ViewPagerHandler;
import com.blunderer.materialdesignlibrary.models.ViewPagerItem;
import com.tonyhu.cookbook.R;
import com.tonyhu.cookbook.db.CuisineType;
import com.tonyhu.cookbook.db.CuisineTypeDao;
import com.tonyhu.cookbook.pageslidinglibrary.PagerSlidingTabStrip;

import java.util.List;

public class CuisineFragment extends Fragment {
    private View rootView;
    private int defaultViewPagerItemSelectedPosition = 0;

    public CuisineFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_cuisine, null);
            initView(rootView);
        }
        return rootView;
    }

    private void initView(View view) {
        List<ViewPagerItem> viewPagerItems = getViewPagerItems();

        if (viewPagerItems != null && viewPagerItems.size() > 0) {
            ViewPager pager = (ViewPager) view.findViewById(R.id.viewpager);
            pager.setAdapter(new ViewPagerWithTabsAdapter(getContext(), getChildFragmentManager(), viewPagerItems));
            if (defaultViewPagerItemSelectedPosition >= 0 && defaultViewPagerItemSelectedPosition < viewPagerItems.size())
                pager.setCurrentItem(defaultViewPagerItemSelectedPosition);

            PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
            tabs.setTextColor(getResources().getColor(android.R.color.white));
            tabs.setViewPager(pager);
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
                tabs.setTabBackground(android.R.attr.selectableItemBackground);
        }
    }

    private List<ViewPagerItem> getViewPagerItems(){
        List<CuisineType> cuisineTypes = new CuisineTypeDao().listAll();
        if(cuisineTypes == null) {
            return null;
        }
        ViewPagerHandler handler = new ViewPagerHandler();
        for(CuisineType cuisineType : cuisineTypes) {
            handler.addPage(cuisineType.getName(),SubCuisineFragment.newInstance(cuisineType.getType(), cuisineType.getName()));
        }
        return handler.getViewPagerItems();
    }

}
