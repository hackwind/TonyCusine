package com.tonyhu.cookbook.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/27.
 */

@DatabaseTable(tableName = "cuisine")
public class Cuisine implements Serializable{
    public final static String STEP_NAME = "name";
    public final static String INGREDIENTS_NAME = "name";
    private static final long serialVersionUID = 12345688l;
    @DatabaseField(generatedId=true ,columnName="id")
    private Integer id;

    @DatabaseField(columnName="cuisine_type")
    private Integer cuisineType;

    @DatabaseField(columnName="category")
    private Integer category;

    @DatabaseField(columnName="name")
    private String name;

    @DatabaseField(columnName="banner_image")
    private String bannerImage;

    @DatabaseField(columnName = "is_favorite")
    private int isFavorite;

    public Cuisine() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(Integer cuisineType) {
        this.cuisineType = cuisineType;
    }

    public Integer getCategory() {return category;}

    public void setCategory(Integer category) {this.category = category;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public int getIsFavorite() { return isFavorite; }

    public void setIsFavorite(int isFavorite) {this.isFavorite = isFavorite;}
}
