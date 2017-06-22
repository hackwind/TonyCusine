package com.tonyhu.cookbook.util;

import android.content.Context;
import android.content.res.AssetManager;
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

    public static Bitmap getAssetsBitmap2(String cuisineName,String picName) {
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

    public static Bitmap getAssetsCategoryBitmap2(String type,String level,String picName) {
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

    /**
     * 对文件进行解密
     * @param level
     * @param fileName
     * @return
     */
    public static Bitmap getAssetsBitmap(String level,String fileName)
    {
        Bitmap image = null;
        AssetManager am = TonyApplication.getContext().getResources().getAssets();
        try
        {
            InputStream is = am.open("菜谱_new/" + level  + "/"  + fileName);
            byte[] buffer = new byte[1024000];//足够大
            int len = is.read(buffer);
            for(int i = 0; i < len; i += 5000){//与加密相同
                byte temp = buffer[i];
                buffer[i] = buffer[i+1];
                buffer[i+1] = temp;
            }
            image = BitmapFactory.decodeByteArray(buffer, 0, len);
            is.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        return image;
    }

    public static Bitmap getAssetsCategoryBitmap(String type,String cuisineName,String picName)
    {
        Bitmap image = null;
        AssetManager am = TonyApplication.getContext().getResources().getAssets();
        try
        {
            InputStream is = am.open("分类_new/" + cuisineName + "/"  + picName);
            byte[] buffer = new byte[1024000];//足够大
            int len = is.read(buffer);
            for(int i = 0; i < len; i += 5000){//与加密相同
                byte temp = buffer[i];
                buffer[i] = buffer[i+1];
                buffer[i+1] = temp;
            }
            image = BitmapFactory.decodeByteArray(buffer, 0, len);
            is.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        return image;
    }
}
