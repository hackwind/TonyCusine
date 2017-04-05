package com.tonyhu.cookbook.db;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/3/27.
 */

public class MottoDao {

    private Dao<Motto,Integer> dao;

    public MottoDao() {
        try {
//            dao = DataBaseHelper.getHelper().getDao(Motto.class);
            dao = DBHelper.getHelper().getDao(Motto.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addType(Motto type) {
        try {
            dao.create(type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Motto get(int id) {
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Motto> listAll() {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
