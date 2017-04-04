package com.tonyhu.cookbook.db;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/3/27.
 */

public class CategoryDao {

    private Dao<Category,Integer> dao;

    public CategoryDao() {
        try {
            dao = DataBaseHelper.getHelper().getDao(Cuisine.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCategory(Category category) {
        try {
            dao.create(category);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Category get(int id) {
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Category> listAll() {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Category> listByCategory(int category) {
        try {
            return  dao.queryBuilder().where().eq("category",category).query();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Category> listByParentCategory(int category) {
        try {
            return  dao.queryBuilder().where().eq("parent_category",category).query();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void update(Category t){
        try {
            dao.update(t);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
