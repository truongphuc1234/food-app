package com.example.demo.repository;

import com.example.demo.model.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

    @Query(nativeQuery = true, value = "SELECT AVG(rating_level) FROM rating WHERE food_id= :foodId")
    Double getAverageRatingByFoodId(Integer foodId);

    @Query(nativeQuery = true, value = "SELECT COUNT(food_id) FROM rating WHERE food_id= :foodId")
    Integer getTotalRatingByFoodId(Integer foodId);

    @Query(nativeQuery = true, value = "SELECT rating_id FROM rating WHERE account_id = :accountId AND food_id = :foodId")
    Integer getRatingId(Integer accountId, Integer foodId);
}
