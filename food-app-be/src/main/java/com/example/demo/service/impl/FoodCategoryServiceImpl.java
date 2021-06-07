package com.example.demo.service.impl;

import com.example.demo.model.entity.Category;
import com.example.demo.repository.FoodCategoryRepository;
import com.example.demo.service.FoodCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodCategoryServiceImpl implements FoodCategoryService {

    private FoodCategoryRepository foodCategoryRepository;

    @Autowired
    public void setFoodCategoryRepository(FoodCategoryRepository foodCategoryRepository) {
        this.foodCategoryRepository = foodCategoryRepository;
    }

    @Override
    public List<Category> getCategoryListByFoodId(Integer foodId) {
        return this.foodCategoryRepository.findAllByFoodId(foodId);
    }
}
