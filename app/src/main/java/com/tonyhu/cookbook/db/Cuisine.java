package com.tonyhu.cookbook.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/27.
 */

@DatabaseTable(tableName = "cuisine")
public class Cuisine implements Serializable{
    private static final long serialVersionUID = 12345688l;
    @DatabaseField(generatedId=true ,columnName="id")
    private Integer id;

    @DatabaseField(columnName="cuisine_type")
    private Integer cuisineType;

    @DatabaseField(columnName="category")
    private Integer category;

    @DatabaseField(columnName="name")
    private String name;

    @DatabaseField(columnName="ingredients")
    private String ingredients;

    @DatabaseField(columnName="cover_image")
    private String coverImage;

    @DatabaseField(columnName="step_image")
    private String stepImage;

    @DatabaseField(columnName="banner_image")
    private String bannerImage;

    @DatabaseField(columnName="steps")
    private String steps;

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

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getStepImage() {
        return stepImage;
    }

    public void setStepImage(String stepImage) {
        this.stepImage = stepImage;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public int getIsFavorite() { return isFavorite; }

    public void setIsFavorite(int isFavorite) {this.isFavorite = isFavorite;}
}
