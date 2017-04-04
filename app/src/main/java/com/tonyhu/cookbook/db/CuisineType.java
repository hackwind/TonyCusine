package com.tonyhu.cookbook.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/27.
 */

@DatabaseTable(tableName = "cuisine_type")
public class CuisineType implements Serializable{
    private static final long serialVersionUID = 12345687l;
    @DatabaseField(generatedId=true,columnName="id")
    private Integer id;
    @DatabaseField(columnName="type")
    private Integer type;
    @DatabaseField(columnName="order")
    private Integer order;
    @DatabaseField(columnName="name")
    private String name;

    public CuisineType() {

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
