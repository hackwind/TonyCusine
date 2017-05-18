package com.tonyhu.cookbook.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tonyhu.cookbook.R;
import com.tonyhu.cookbook.util.ImageUtil;
import com.tonyhu.cookbook.widget.PhotoViewPager;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by Administrator on 2017/5/16.
 */

public class PhotoViewActivity extends BaseActivity  {

    public static final String TAG = PhotoViewActivity.class.getSimpleName();
    private PhotoViewPager mViewPager;
    private int currentPosition;
    private MyImageAdapter adapter;
    private TextView mTvImageCount;
    private TextView mTvDesc;
    private String[] urls;
    private String[] descs;
    private String cusineName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranslucentStatus();
        setContentView(R.layout.activity_photo_view);
        initView();
        initData();
    }

    private void initView() {
        mViewPager = (PhotoViewPager) findViewById(R.id.view_pager_photo);
        mTvImageCount = (TextView) findViewById(R.id.tv_image_count);
        mTvDesc = (TextView) findViewById(R.id.tv_desc);
    }

    private void initData() {

        Intent intent = getIntent();
        currentPosition = intent.getIntExtra("currentPosition", 0);
        urls = intent.getStringArrayExtra("urls");
        descs = intent.getStringArrayExtra("descs");
        cusineName = intent.getStringExtra("cuisine");

        adapter = new MyImageAdapter(urls, this);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(currentPosition, false);
        mTvImageCount.setText(currentPosition+1 + "/" + urls.length);
        mTvDesc.setText(descs[currentPosition]);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentPosition = position;
                mTvImageCount.setText(currentPosition + 1 + "/" + urls.length);
                mTvDesc.setText(descs[position]);
            }
        });
    }


    class MyImageAdapter extends PagerAdapter {
        public final String TAG = MyImageAdapter.class.getSimpleName();
        private String[] imageUrls;
        private AppCompatActivity activity;

        public MyImageAdapter(String[] imageUrls, AppCompatActivity activity) {
            this.imageUrls = imageUrls;
            this.activity = activity;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            String url = imageUrls[position];
            PhotoView photoView = new PhotoView(activity);

            Bitmap bitmap = ImageUtil.getAssetsBitmap(cusineName, url);
            if(bitmap != null) {
                photoView.setImageBitmap(bitmap);
            } else {
                photoView.setImageResource(R.drawable.default_no_pic);
            }
            container.addView(photoView);
            photoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.finish();
                }
            });
            return photoView;
        }

        @Override
        public int getCount() {
            return imageUrls != null ? imageUrls.length : 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }
}