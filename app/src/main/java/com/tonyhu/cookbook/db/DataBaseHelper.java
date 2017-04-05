package com.tonyhu.cookbook.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.tonyhu.cookbook.TonyApplication;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/3/27.
 */

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {
    private static  String DATABASE_NAME = "lovefood";
    private static int VERSION = 1;

    private static DataBaseHelper mInstance;
    private HashMap<String,Dao> daos = new HashMap<>();

    public DataBaseHelper(Context context) {
        super(context,DATABASE_NAME,null,VERSION);
    }

    public static synchronized DataBaseHelper getHelper() {
        synchronized (DataBaseHelper.class) {
            if(mInstance == null) {
                mInstance = new DataBaseHelper(TonyApplication.getContext());
            }
            return mInstance;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource,CuisineType.class);
            TableUtils.createTable(connectionSource,Cuisine.class);
            TableUtils.createTable(connectionSource,Motto.class);
            TableUtils.createTable(connectionSource,Category.class);
            initData(sqLiteDatabase,"cuisine_type.sql");
            initData(sqLiteDatabase,"category.sql");
            initData(sqLiteDatabase,"cuisine.sql");
            initData(sqLiteDatabase,"motto.sql");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource,CuisineType.class,true);
            TableUtils.dropTable(connectionSource,Cuisine.class,true);
            TableUtils.dropTable(connectionSource,Motto.class,true);
            TableUtils.dropTable(connectionSource,Category.class,true);
            onCreate(sqLiteDatabase,connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized Dao getDao(Class clazz) throws SQLException
    {
        Dao dao = null;
        String className = clazz.getSimpleName();

        if (daos.containsKey(className))
        {
            dao = daos.get(className);
        }
        if (dao == null)
        {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close()
    {
        super.close();
        Dao dao = null;
        for (String key : daos.keySet())
        {
            dao = daos.get(key);
            dao = null;
        }
    }

    private void initData(SQLiteDatabase db,String sqlName) {
        try {
            InputStream is = TonyApplication.getContext().getAssets().open(sqlName);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);
            String line;
            db.beginTransaction();
            while ((line = reader.readLine()) != null && !TextUtils.isEmpty(line)) {
                db.execSQL(line);
            }
            db.setTransactionSuccessful();
            db.endTransaction();
            is.close();
            isr.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
