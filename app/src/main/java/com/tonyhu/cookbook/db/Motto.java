package com.tonyhu.cookbook.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/27.
 */

@DatabaseTable(tableName = "motto")
public class Motto implements Serializable{
    private static final long serialVersionUID = 12345689l;
    @DatabaseField(generatedId=true,columnName="id")
    private Integer id;
    @DatabaseField(columnName = "motto")
    private String motto;

    public Motto() {

    }
    public Integer getId() {
        return id;
    }


    public String getMotto() {
        return motto;
    }

    public void setMotto(String name) {
        this.motto = name;
    }
}
