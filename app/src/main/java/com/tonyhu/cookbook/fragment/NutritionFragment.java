package com.tonyhu.cookbook.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tonyhu.cookbook.R;

public class NutritionFragment extends Fragment {
    private View rootView;

    public NutritionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_nutrition, null);
            initView(rootView);
        }
        return rootView;
    }

    private void initView(View view) {
    }

}
