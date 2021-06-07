package com.example.demo.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "food_status")
public class FoodStatus {

    @Id
    @Column(name = "food_status_id")
    private Integer foodStatusId;

    @Column(name = "food_status_name")
    private String foodStatusName;

    public Integer getFoodStatusId() {
        return foodStatusId;
    }

    public void setFoodStatusId(Integer foodStatusId) {
        this.foodStatusId = foodStatusId;
    }

    public String getFoodStatusName() {
        return foodStatusName;
    }

    public void setFoodStatusName(String foodStatusName) {
        this.foodStatusName = foodStatusName;
    }
}
