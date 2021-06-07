package com.example.demo.service;

import com.example.demo.model.entity.Category;

import java.util.List;

public interface FoodCategoryService {
    List<Category> getCategoryListByFoodId(Integer foodId);
}
