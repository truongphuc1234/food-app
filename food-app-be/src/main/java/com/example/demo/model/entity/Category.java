package com.example.demo.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "category_name")
    private String categoryName;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer foodCategoryId) {
        this.categoryId = foodCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String foodCategoryName) {
        this.categoryName = foodCategoryName;
    }
}
