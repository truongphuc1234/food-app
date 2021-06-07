package com.example.demo.model.dto;

import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.Food;

import java.util.List;

public class FoodDetailDTO {

    private Food food;
    private List<Category> categories;
    private FoodSummaryDTO foodSummary;

    public FoodDetailDTO(Food food, List<Category> categories, FoodSummaryDTO foodSummary) {
        this.food = food;
        this.categories = categories;
        this.foodSummary = foodSummary;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public FoodSummaryDTO getFoodSummary() {
        return foodSummary;
    }

    public void setFoodSummary(FoodSummaryDTO foodSummary) {
        this.foodSummary = foodSummary;
    }
}
