package com.tonyhu.cookbook.db;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/3/27.
 */

public class CuisineDao {

    private Dao<Cuisine,Integer> dao;

    public CuisineDao() {
        try {
//            dao = DataBaseHelper.getHelper().getDao(Cuisine.class);
            dao = DBHelper.getHelper().getDao(Cuisine.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCuisine(Cuisine cuisine) {
        try {
            dao.create(cuisine);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Cuisine get(int id) {
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Cuisine> listAll() {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Cuisine> listByCuisineType(int type) {
        try {
            return  dao.queryBuilder().where().eq("cuisine_type",type).query();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Cuisine> listByCategory(int category) {
        try {
            return  dao.queryBuilder().where().eq("category",category).query();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Cuisine> listFavorites() {
        try {
            return  dao.queryBuilder().where().eq("is_favorite",1).query();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Cuisine> search(String keyword) {
        try {
            return  dao.queryBuilder().where().like("name","%" + keyword + "%").query();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void update(Cuisine t){
        try {
            dao.update(t);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
