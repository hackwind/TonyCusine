package com.tonyhu.cookbook.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.tonyhu.cookbook.R;
import com.tonyhu.cookbook.db.Cuisine;
import com.tonyhu.cookbook.db.CuisineDao;
import com.tonyhu.cookbook.util.ImageUtil;
import com.tonyhu.cookbook.util.ScreenUtil;


/*Created by Administrator on 2017/3/29.
 */

public class CuisineDetailActivity extends BaseActivity {

    private ImageView bannerView;
    private TextView title;
    private RecyclerView ryMaterials;
    private RecyclerView rySteps;
    private RecyclerView.Adapter materialAdapter;
    private RecyclerView.Adapter stepsAdapter;
    private ImageView addFavorite;
    private TextView addFavoriteAnim;

    private String[] materialsNames;
    private String[] materialsValues;
    private String[] steps;
    private String[] step_images;
    private int cuisineId;
    private String cusineTypeName;
    private String cusineName;
    private int isFavor = 0;

    private CuisineDao dao;
    private Cuisine cuisine;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suisine_detail);
        cuisineId = getIntent().getIntExtra("cuisine_id",0);
        cusineTypeName = getIntent().getStringExtra("cuisine_type_name");
        cusineName = getIntent().getStringExtra("cuisine_name");
        initView();
        getData();
    }

    private void initView() {
        bannerView = (ImageView)findViewById(R.id.detail_banner);
        title = (TextView)findViewById(R.id.detail_title);
        ryMaterials = (RecyclerView)findViewById(R.id.detail_recycleview_material);
        rySteps = (RecyclerView)findViewById(R.id.detail_recycleview_steps);
        addFavorite = (ImageView)findViewById(R.id.detail_addFavorite);
        addFavoriteAnim = (TextView)findViewById(R.id.addfav_anim);

        GridLayoutManager matLayoutManager = new GridLayoutManager(this, 2){
            @Override
            public boolean canScrollVertically() {
                return false;//避免在外部嵌套ScrollView时容易卡主,胡爸真棒^_^
            }
        };
        matLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        ryMaterials.setLayoutManager(matLayoutManager);
        GridLayoutManager stepLayoutManager = new GridLayoutManager(this, 1) {
            @Override
            public boolean canScrollVertically() {
                return false;//避免在外部嵌套ScrollView时容易卡主,胡爸真棒^_^
            }
        };
        stepLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        rySteps.setLayoutManager(stepLayoutManager);

        materialAdapter = new RecyclerView.Adapter<MaterialHolder>() {
            @Override
            public MaterialHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                @SuppressLint("InflateParams")
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_material, null);
                return new MaterialHolder(view);
            }

            @Override
            public void onBindViewHolder(MaterialHolder holder, int position) {
                holder.bind(position);
            }

            @Override
            public int getItemCount() {
                return materialsNames == null ? 0 : materialsNames.length * 2;
            }
        };
        ryMaterials.setAdapter(materialAdapter);

        stepsAdapter = new RecyclerView.Adapter<StepHolder>() {
            @Override
            public StepHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                @SuppressLint("InflateParams")
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_steps, null);
                return new StepHolder(view);
            }

            @Override
            public void onBindViewHolder(StepHolder holder, int position) {
                holder.bind(position);
            }

            @Override
            public int getItemCount() {
                return steps == null ? 0 : steps.length;
            }
        };
        rySteps.setAdapter(stepsAdapter);

        addFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFavor == 1) {
                    updateFavorStatus(0);
                } else {
                    updateFavorStatus(1);
                }
                addFavoriteAnim.setVisibility(View.VISIBLE);
                addFavoriteAnim.startAnimation(AnimationUtils.loadAnimation(CuisineDetailActivity.this,R.anim.add_favorite_anim));
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        addFavoriteAnim.setVisibility(View.GONE);
                    }
                }, 1000);

            }
        });
    }

    private void updateFavorStatus(int status) {
        isFavor = status;
        if(status == 0) {
            addFavoriteAnim.setText(R.string.remove_favorite);
            addFavorite.setImageResource(R.drawable.remove_favorite);
        } else {
            addFavoriteAnim.setText(R.string.add_favorite);
            addFavorite.setImageResource(R.drawable.add_favorite);
        }
        if(dao != null && cuisine != null) {
            cuisine.setIsFavorite(status);
            dao.update(cuisine);
        }
    }

    private void getData(){
        if(cuisineId <= 0) {
            return;
        }
        dao = new CuisineDao();
        cuisine = dao.get(cuisineId);
        if(cuisine == null) {
            return ;
        }
        String bannerIUrl = cuisine.getBannerImage();
        if(!TextUtils.isEmpty(bannerIUrl)) {
            Bitmap bitmap = ImageUtil.getAssetsBitmap(cusineTypeName,cusineName,bannerIUrl);
            if(bitmap != null) {
                bannerView.setImageBitmap(bitmap);
                int width = ScreenUtil.getScreenWidth();
                int height =  width * 225 / 300;
                ViewGroup.LayoutParams params = bannerView.getLayoutParams();
                params.width = width;
                params.height = height;
                bannerView.setLayoutParams(params);
            }
        }
        title.setText(cuisine.getName());

        isFavor = cuisine.getIsFavorite();
        if(isFavor == 1) {
            addFavorite.setImageResource(R.drawable.add_favorite);
        }

        String material = cuisine.getIngredients();
        if(!TextUtils.isEmpty(material)) {
            String[] materials = material.split(";");
            String[] nameValue;
            materialsNames = new String[materials.length];
            materialsValues = new String[materials.length];
            for(int i = 0; i < materials.length;i++){
                nameValue = materials[i].split(":");
                materialsNames[i] = nameValue[0];
                materialsValues[i] = nameValue[1];
            }
        }

        String step = cuisine.getSteps();
        if(!TextUtils.isEmpty(step)){
            steps = step.split("。");
        }

        String stepImg = cuisine.getStepImage();
        if(!TextUtils.isEmpty(stepImg)) {
            step_images = stepImg.split(";");
        }
    }

    class MaterialHolder extends RecyclerView.ViewHolder {
        TextView name;

        public MaterialHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.material);
        }

        public void bind(int position) {
            if(position % 2 == 0) {
                name.setText(materialsNames[position / 2] + ":");
            } else {
                name.setText(materialsValues[position / 2 ] );
            }

        }
    }

    class StepHolder extends RecyclerView.ViewHolder {
        TextView step;
        ImageView image;

        public StepHolder(View itemView) {
            super(itemView);
            step = (TextView)itemView.findViewById(R.id.step);
            image = (ImageView)itemView.findViewById(R.id.step_image);
        }

        public void bind(int position) {
           step.setText((position + 1) + "." + steps[position]);
            if(step_images != null && !TextUtils.isEmpty(step_images[position]) && !"null".equals(step_images[position])) {
                Bitmap bitmap = ImageUtil.getAssetsBitmap(cusineTypeName, cusineName, step_images[position]);
                if(bitmap != null) {
                    image.setImageBitmap(bitmap);
                    image.setVisibility(View.VISIBLE);
                } else {
                    image.setVisibility(View.GONE);
                }
            } else {
                image.setVisibility(View.GONE);
            }

        }
    }

}
