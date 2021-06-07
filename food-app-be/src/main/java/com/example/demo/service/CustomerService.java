package com.example.demo.service;

import com.example.demo.model.entity.Customer;

public interface CustomerService {
    Customer getCustomerByCustomerId(Integer customerId);
}
