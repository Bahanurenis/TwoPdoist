package com.example.bahanur.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by yoda on 9.5.2015.
 */
public class Category {
    public Category(){
    }

    public Category(String categoryName){
        this.categoryName = categoryName;
    }

    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField
   private String categoryName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
