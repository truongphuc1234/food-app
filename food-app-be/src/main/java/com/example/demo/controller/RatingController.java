package com.example.demo.controller;

import com.example.demo.model.dto.FoodSummaryDTO;
import com.example.demo.model.entity.Rating;
import com.example.demo.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/rating")
public class RatingController {

    private RatingService ratingService;

    @Autowired
    public void setRatingService(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> saveRating(@RequestBody Rating rating) {
        ratingService.saveRating(rating);
        FoodSummaryDTO foodSummary = new FoodSummaryDTO(this.ratingService.getAverageRatingByFoodId(rating.getFood().getFoodId()),
                this.ratingService.getTotalRatingByFoodId(rating.getFood().getFoodId()),
                null,
                null);
        return new ResponseEntity<>(foodSummary, HttpStatus.OK);
    }
}
