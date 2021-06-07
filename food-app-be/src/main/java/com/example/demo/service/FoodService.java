package com.example.demo.service;

import com.example.demo.model.dto.FoodQueryDTO;
import com.example.demo.model.entity.Food;
import org.springframework.data.domain.Page;

public interface FoodService {
    Page<Food> findAllByQuery(FoodQueryDTO foodQueryDTO);

    Food findById(Integer foodId);
}
