package com.example.demo.service.impl;

import com.example.demo.model.dto.FoodQueryDTO;
import com.example.demo.model.entity.Food;
import com.example.demo.repository.FoodRepository;
import com.example.demo.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FoodServiceImpl implements FoodService {

    private FoodRepository foodRepository;

    @Autowired
    public void setFoodRepository(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public Page<Food> findAllByQuery(FoodQueryDTO foodQueryDTO) {
        Pageable pageable = PageRequest.of(foodQueryDTO.getPage(), foodQueryDTO.getSize());

        return foodRepository.findAllByQuery(pageable,
                foodQueryDTO.getName(),
                foodQueryDTO.getLowPrice(),
                foodQueryDTO.getHighPrice(),
                foodQueryDTO.getLowRating(),
                foodQueryDTO.getHighRating(),
                foodQueryDTO.getCategories(),
                foodQueryDTO.getStatuses());
    }

    @Override
    public Food findById(Integer foodId) {
        return foodRepository.findById(foodId).orElse(null);
    }
}
