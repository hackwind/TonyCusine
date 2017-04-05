package com.tonyhu.cookbook.db;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/3/27.
 */

public class CuisineTypeDao {

    private Dao<CuisineType,Integer> dao;

    public CuisineTypeDao() {
        try {
//            dao = DataBaseHelper.getHelper().getDao(CuisineType.class);
            dao = DBHelper.getHelper().getDao(CuisineType.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addType(CuisineType cuisineType) {
        try {
            dao.create(cuisineType);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public CuisineType get(int id) {
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<CuisineType> listAll() {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
