package com.example.demo.service.impl;

import com.example.demo.model.entity.Rating;
import com.example.demo.repository.RatingRepository;
import com.example.demo.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {

    private RatingRepository ratingRepository;

    @Autowired
    public void setRatingRepository(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public Double getAverageRatingByFoodId(Integer foodId) {
        return this.ratingRepository.getAverageRatingByFoodId(foodId);
    }

    @Override
    public Integer getTotalRatingByFoodId(Integer foodId) {
        return this.ratingRepository.getTotalRatingByFoodId(foodId);
    }

    @Override
    public void saveRating(Rating rating) {
        Integer ratingId = this.ratingRepository.getRatingId(rating.getAccount().getAccountId(),rating.getFood().getFoodId());
        rating.setRatingId(ratingId);
        this.ratingRepository.save(rating);
    }
}
