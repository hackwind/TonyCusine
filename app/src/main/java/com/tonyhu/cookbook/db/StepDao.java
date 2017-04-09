package com.tonyhu.cookbook.db;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/3/27.
 */

public class StepDao {

    private Dao<Step,Integer> dao;

    public StepDao() {
        try {
//            dao = DataBaseHelper.getHelper().getDao(Motto.class);
            dao = DBHelper.getHelper().getDao(Step.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Step get(int id) {
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Step> listByName(String name) {
        try {
            return dao.queryBuilder().where().eq(Step.NAME, name).query();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        }
    }
}
