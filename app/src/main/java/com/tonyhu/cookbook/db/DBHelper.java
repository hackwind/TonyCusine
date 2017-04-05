package com.tonyhu.cookbook.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.tonyhu.cookbook.R;
import com.tonyhu.cookbook.TonyApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/3/27.
 */

public class DBHelper  {
    private static  String DATABASE_NAME = "lovefood";
    private static int VERSION = 1;

    private static DBHelper mInstance;
    private HashMap<String,Dao> daos = new HashMap<>();
    private AndroidConnectionSource connectionSource;

    public DBHelper(Context context) {
        File dir = new File("/data/data/" + context.getPackageName() + "/databases");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, DATABASE_NAME);
        if (!file.exists()) {
            try {
                copyFile(context, file, R.raw.lovefood);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        SQLiteDatabase db = SQLiteDatabase.openDatabase(file.getPath(), null,
                SQLiteDatabase.OPEN_READWRITE);
        connectionSource = new AndroidConnectionSource(db);
    }


    public static void copyFile(Context context, File file, int id)
            throws IOException {
        InputStream is = context.getResources().openRawResource(id);
        FileOutputStream fos = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count = 0;
        while ((count = is.read(buffer)) > 0) {
            fos.write(buffer, 0, count);
        }
        fos.close();
        is.close();
    }

    public static synchronized DBHelper getHelper() {
        synchronized (DBHelper.class) {
            if(mInstance == null) {
                mInstance = new DBHelper(TonyApplication.getContext());
            }
            return mInstance;
        }
    }

    public <D extends Dao<T, ?>, T> D getDao(Class<T> clazz) throws SQLException {
        if (connectionSource != null) {
            return DaoManager.createDao(connectionSource, clazz);
        }
        return null;
    }




}
