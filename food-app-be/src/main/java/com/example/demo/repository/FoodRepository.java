package com.example.demo.repository;

import com.example.demo.model.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends PagingAndSortingRepository<Food, Integer> {


    @Query(value = "SELECT f.* FROM `food` f " +
            "LEFT JOIN `food_category` fc ON f.food_id = fc.food_id " +
            "LEFT JOIN (SELECT food_id, AVG(rating_level) AS avg_rating FROM rating GROUP BY food_id) rt ON rt.food_id = f.food_id " +
            "WHERE (:#{#categories.size()} = 0 OR fc.category_id IN (:categories)) AND " +
            "(:#{#statuses.size()} = 0 OR f.food_status_id IN (:statuses)) AND " +
            "(:name IS NULL OR f.food_name LIKE CONCAT('%',:name,'%')) AND " +
            "(:lowPrice IS NULL OR f.food_price >= :lowPrice) AND " +
            "(:highPrice IS NULL OR f.food_price <= :highPrice) AND " +
            "(:lowRating IS NULL OR IFNULL(rt.avg_rating, 5) >= :lowPrice) AND " +
            "(:highRating IS NULL OR IFNULL(rt.avg_rating, 5) <= :highPrice) " +
            "GROUP BY f.food_id",
            countQuery = "SELECT COUNT(DISTINCT f.food_id) FROM `food` f " +
                    "LEFT JOIN `food_category` fc ON f.food_id = fc.food_id " +
                    "LEFT JOIN (SELECT food_id, AVG(rating_level) AS avg_rating FROM rating GROUP BY food_id) rt ON rt.food_id = f.food_id " +
                    "WHERE (:#{#categories.size()} = 0 OR fc.category_id IN (:categories)) AND " +
                    "(:#{#statuses.size()} = 0 OR f.food_status_id IN (:statuses)) AND " +
                    "(:name IS NULL OR f.food_name LIKE CONCAT('%',:name,'%')) AND " +
                    "(:lowPrice IS NULL OR f.food_price >= :lowPrice) AND " +
                    "(:highPrice IS NULL OR f.food_price <= :highPrice) AND " +
                    "(:lowRating IS NULL OR IFNULL(rt.avg_rating, 5) >= :lowPrice) AND " +
                    "(:highRating IS NULL OR IFNULL(rt.avg_rating, 5) <= :highPrice) " +
                    "GROUP BY f.food_id",
            nativeQuery = true)
    Page<Food> findAllByQuery(Pageable pageable,
                               String name,
                               Double lowPrice,
                               Double highPrice,
                               Integer lowRating,
                               Integer highRating,
                               List<Integer> categories,
                               List<Integer> statuses);

}
