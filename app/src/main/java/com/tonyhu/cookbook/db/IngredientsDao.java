package com.tonyhu.cookbook.db;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/3/27.
 */

public class IngredientsDao {

    private Dao<Ingredients,Integer> dao;

    public IngredientsDao() {
        try {
//            dao = DataBaseHelper.getHelper().getDao(Motto.class);
            dao = DBHelper.getHelper().getDao(Ingredients.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Ingredients get(int id) {
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Ingredients> listByName(String name) {
        try {
            return dao.queryBuilder().where().eq(Ingredients.NAME,name).query();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
