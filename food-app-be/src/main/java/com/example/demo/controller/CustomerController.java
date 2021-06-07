package com.example.demo.controller;

import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getCustomerByCustomerId(@PathVariable("id") Integer customerId) {
        return new ResponseEntity<>(this.customerService.getCustomerByCustomerId(customerId), HttpStatus.OK);
    }


}
