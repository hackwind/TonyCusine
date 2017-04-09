package com.tonyhu.cookbook.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/27.
 */

@DatabaseTable(tableName = "steps")
public class Step implements Serializable{
    private static final long serialVersionUID = 12345691l;
    public final static String NAME = "name";

    @DatabaseField(generatedId=true,columnName="id")
    private Integer id;
    @DatabaseField(columnName="order")
    private Integer order;
    @DatabaseField(columnName="step")
    private String step;
    @DatabaseField(columnName="pic")
    private String pic;
    @DatabaseField(columnName=NAME)
    private String cuisineName;

    public Step() {

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getCuisineName() {
        return cuisineName;
    }

    public void setCuisineName(String cuisineName) {
        this.cuisineName = cuisineName;
    }

    public String getStep() {return this.step;}

    public void setStep(String step) {this.step = step;}

    public String getPic() {return this.pic;}

    public void setPic(String pic) {this.pic = pic;}
}
