package com.tonyhu.cookbook.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/6/23.
 */

public class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap> {

    private AsyncImageLoader imageLoader;
    // 显示图片控件视图
    private final WeakReference<ImageView> imageViewReference;
    // 图片Url地址
    protected String imageUrl;

    public BitmapWorkerTask(AsyncImageLoader imageLoader, ImageView view) {
        this.imageLoader = imageLoader;
        this.imageViewReference = new WeakReference<ImageView>(view);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        imageUrl = params[0];
        // 通过url下载图片
//        Bitmap bitmap = downloadBitmap(params[0]);
        Bitmap bitmap = getBitmapFromAssets(params);
        if (bitmap != null) {
            // 将图片放入内存缓存中
            if("1".equals(params[0])) {
                imageLoader.addBitmapToMemoryCache(params[1] + "/" + params[2], bitmap);
            } else {
                imageLoader.addBitmapToMemoryCache(params[1], bitmap);
            }
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        // 通过tag返回一个imageview对象
        ImageView imageView = imageViewReference.get();
        if (imageView != null) {
            if (result != null) {
                // 加载成功
                imageView.setImageBitmap(result);
            } else {
                // 加载失败
                if (imageLoader.loadfailBitmap != null) {
                    imageView.setImageBitmap(imageLoader.loadfailBitmap);
                }
            }
        }
        imageLoader.taskCollection.remove(this);
    }

    private Bitmap getBitmapFromAssets(String... params) {
        Bitmap bitmap = null;
        try {
            if("1".equals(params[0])) {
                bitmap = ImageUtil.getAssetsBitmap(params[1], params[2]);
            } else {
                bitmap = ImageUtil.getAssetsCategoryBitmap(params[1]);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } catch (OutOfMemoryError o) {
            o.printStackTrace();
            System.gc();
        }
        return bitmap;
    }

    /**
     * 通过http协议，根据url返回bitmap对象
     *
     * @param imgUrl
     * @return
     */
    private Bitmap downloadBitmap(String imgUrl) {
        Bitmap bitmap = null;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(imgUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(6 * 1000);
            conn.setReadTimeout(10 * 1000);
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return bitmap;
    }

}
