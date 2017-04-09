package com.tonyhu.cookbook.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.tonyhu.cookbook.TonyApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/3/29.
 */

public class ImageUtil {

    public static Bitmap getAssetsBitmap(String cuisineName,String picName) {
        InputStream is = null;
        try {
            is = TonyApplication.getContext().getAssets().open("菜谱/" + cuisineName + "/"  + picName);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if(is != null) {
                try {is.close();} catch (IOException e) {e.printStackTrace();}
            }
        }
    }

    public static Bitmap getAssetsCategoryBitmap(String typeName,String level,String picName) {
        InputStream is = null;
        try {
            is = TonyApplication.getContext().getAssets().open("分类/" + level  + "/"  + picName);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if(is != null) {
                try {is.close();} catch (IOException e) {e.printStackTrace();}
            }
        }
    }
}
