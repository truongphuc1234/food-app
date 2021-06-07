package com.example.demo.repository;

import com.example.demo.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodCategoryRepository extends JpaRepository<Category,Integer> {
    @Query(value = "SELECT c.* FROM food_category fc LEFT JOIN category c ON c.category_id = fc.category_id WHERE fc.food_id = :foodId GROUP BY c.category_id",nativeQuery = true)
    List<Category> findAllByFoodId(Integer foodId);
}
