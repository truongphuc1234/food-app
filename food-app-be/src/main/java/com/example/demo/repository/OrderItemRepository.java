package com.example.demo.repository;

import com.example.demo.model.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
    @Query(nativeQuery = true, value = "SELECT SUM(quantity) FROM order_item WHERE food_id = :foodId")
    Integer getTotalQuantityByFoodId(Integer foodId);

}
