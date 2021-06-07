package com.example.demo.model.entity;

import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "food")
public class Food {

    @Id
    @Column(name = "food_id")
    private Integer foodId;

    @Column(name = "food_name")
    private String foodName;

    @Column(name = "food_price")
    private Double foodPrice;

    @ManyToOne
    @JoinColumn(name = "food_status_id", referencedColumnName = "food_status_id")
    private FoodStatus foodStatus;

    @Column(name = "food_time_post")
    private LocalDate foodTimePost;

    @Column(name = "food_image")
    private String foodImage;

    @Column(name = "food_description")
    private String foodDescription;

    @Column(name = "food_update_time")
    private LocalDate foodUpdateTime;

    @Column(name = "discount")
    private Double discount;

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public FoodStatus getFoodStatus() {
        return foodStatus;
    }

    public void setFoodStatus(FoodStatus foodStatus) {
        this.foodStatus = foodStatus;
    }

    public LocalDate getFoodTimePost() {
        return foodTimePost;
    }

    public void setFoodTimePost(LocalDate foodTimePost) {
        this.foodTimePost = foodTimePost;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public LocalDate getFoodUpdateTime() {
        return foodUpdateTime;
    }

    public void setFoodUpdateTime(LocalDate foodUpdateTime) {
        this.foodUpdateTime = foodUpdateTime;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
