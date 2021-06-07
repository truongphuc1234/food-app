package com.example.demo.service.impl;

import com.example.demo.repository.OrderItemRepository;
import com.example.demo.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private OrderItemRepository orderItemRepository;

    @Autowired
    public void setOrderItemRepository(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public Integer getTotalQuantityByFoodId(Integer foodId) {
        return orderItemRepository.getTotalQuantityByFoodId(foodId);
    }
}
