package com.example.demo.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "food_category")
public class FoodCategory {

    @Id
    @Column(name = "food_category_id")
    private Integer foodCategoryId;

    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "food_id",referencedColumnName = "food_id")
    private Food food;

    public Integer getFoodCategoryId() {
        return foodCategoryId;
    }

    public void setFoodCategoryId(Integer foodCategoryId) {
        this.foodCategoryId = foodCategoryId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
