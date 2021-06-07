package com.example.demo.service;

import com.example.demo.model.entity.Rating;

public interface RatingService {
    Double getAverageRatingByFoodId(Integer foodId);

    Integer getTotalRatingByFoodId(Integer foodId);

    void saveRating(Rating rating);
}
