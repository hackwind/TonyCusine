package com.tonyhu.cookbook.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/27.
 */

@DatabaseTable(tableName = "ingredients")
public class Ingredients implements Serializable{
    private static final long serialVersionUID = 12345691l;
    public final static String NAME = "name";

    @DatabaseField(generatedId=true,columnName="id")
    private Integer id;
    @DatabaseField(columnName="key")
    private String key;
    @DatabaseField(columnName="value")
    private String value;
    @DatabaseField(columnName=NAME)
    private String cuisineName;

    public Ingredients() {

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCuisineName() {
        return cuisineName;
    }

    public void setCuisineName(String cuisineName) {
        this.cuisineName = cuisineName;
    }

    public String getKey() {return this.key;}

    public void setKey(String key) {this.key = key;}

    public String getValue() {return this.value;}

    public void setValue(String value) {this.value = value;}
}
