package com.tonyhu.cookbook.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.tonyhu.cookbook.R;
import com.tonyhu.cookbook.activity.CuisineDetailActivity;
import com.tonyhu.cookbook.activity.FoodSubTypeActivity;
import com.tonyhu.cookbook.db.Category;
import com.tonyhu.cookbook.db.CategoryDao;
import com.tonyhu.cookbook.db.Cuisine;
import com.tonyhu.cookbook.db.CuisineDao;
import com.tonyhu.cookbook.util.ImageUtil;
import com.tonyhu.cookbook.util.ScreenUtil;

import java.util.List;

public class FoodTypeFragment extends Fragment {
    private final static String TYPE = "type";
    private final static String NAME = "name";
    private final static int PADDING_OUTSIDE = 20;
    private final static int PADDING_INSIDE = 10;
    private View rootView;
    private RecyclerView.Adapter adapter;
    private List<Category> categoryItems;
    private int category;
    private String name;

    public FoodTypeFragment() {
    }

    public static FoodTypeFragment newInstance(int category,String name) {
        FoodTypeFragment fragment = new FoodTypeFragment();
        Bundle args = new Bundle();
        args.putInt("category",category);
        args.putString("name",name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        getData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_sub_cuisine, null);
            initView(rootView);
        }
        return rootView;
    }

    private void initView(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listview);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerView.Adapter<Holder>() {
            @Override
            public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
                @SuppressLint("InflateParams")
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subcuisine_list, null);
                return new Holder(view);
            }

            @Override
            public void onBindViewHolder(Holder holder, int position) {
                holder.bind(position);

                int screenWidth = ScreenUtil.getScreenWidth();
                int width = (screenWidth - PADDING_INSIDE *2 - PADDING_OUTSIDE * 2) / 2;
                int height = width + (int)getResources().getDimension(R.dimen.cardview_title_height);

                CardView.LayoutParams params = new CardView.LayoutParams(width,height);
                params.gravity = Gravity.CENTER_HORIZONTAL;
                params.bottomMargin = PADDING_OUTSIDE;
                params.topMargin = PADDING_OUTSIDE;
                if(position%2 == 0) {
                    params.leftMargin = PADDING_OUTSIDE;
                    params.rightMargin = PADDING_INSIDE;
                } else {
                    params.leftMargin = PADDING_INSIDE;
                    params.rightMargin = PADDING_OUTSIDE;
                }
                holder.itemView.setLayoutParams(params);
                ImageView img = (ImageView)holder.itemView.findViewById(R.id.sub_image);
                ViewGroup.LayoutParams imgParams = img.getLayoutParams();
                imgParams.width = imgParams.height = width;
                img.setLayoutParams(imgParams);
            }

            @Override
            public int getItemCount() {
                return categoryItems == null ? 0 : categoryItems.size();
            }
        };
        recyclerView.setAdapter(adapter);
    }

    private void getData() {
        if(getArguments() != null) {
            category = getArguments().getInt("category", 0);
        } else {
            category = 0;
        }
        if(category == 0) {
            CategoryDao dao = new CategoryDao();
            categoryItems = dao.listByParentCategory(category);
        }
        if(categoryItems == null) {
            return;
        }

        if(adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    class Holder extends RecyclerView.ViewHolder {
        ImageView subImage;
        TextView subTitle;

        public Holder(View itemView) {
            super(itemView);
            subImage = (ImageView)itemView.findViewById(R.id.sub_image);
            subTitle = (TextView)itemView.findViewById(R.id.sub_title);
        }

        public void bind(int position) {
            final int finalPosition = position;
            final Category category = categoryItems.get(position);
            String cover = category.getImage();
            if(cover == null) {
                // set default image
                subImage.setImageResource(R.drawable.default_image);
            } else {
                Bitmap bitmap = ImageUtil.getAssetsCategoryBitmap(name ,"一级" , cover);
                if(bitmap != null) {
                    subImage.setImageBitmap(bitmap);
                }
            }
            subTitle.setText(category.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                @TargetApi(Build.VERSION_CODES.KITKAT_WATCH)
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), FoodSubTypeActivity.class);
                    intent.putExtra("category", category.getCategory());
                    intent.putExtra("category_name", category.getName());
                    startActivity(intent);
                }
            });

        }
    }
}
