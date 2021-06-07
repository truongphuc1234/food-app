package com.example.demo.controller;

import com.example.demo.model.dto.FoodDetailDTO;
import com.example.demo.model.dto.FoodQueryDTO;
import com.example.demo.model.dto.FoodSummaryDTO;
import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.Food;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/food")
public class FoodController {

    private FoodService foodService;
    private CategoryService categoryService;
    private CommentService commentService;
    private RatingService ratingService;
    private OrderItemService orderItemService;

    @Autowired
    public void setFoodCategoryService(FoodCategoryService foodCategoryService) {
        this.foodCategoryService = foodCategoryService;
    }

    private FoodCategoryService foodCategoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setFoodService(FoodService foodService) {
        this.foodService = foodService;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    public void setRatingService(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @Autowired
    public void setOrderItemService(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping("/list")
    public ResponseEntity<?> getListFood(@RequestBody FoodQueryDTO foodQueryDTO) {
        return new ResponseEntity<>(foodService.findAllByQuery(foodQueryDTO), HttpStatus.OK);
    }

    @GetMapping("/category-list")
    public ResponseEntity<?> getCategoryList() {
        return new ResponseEntity<>(categoryService.getCategoryList(), HttpStatus.OK);
    }

    @Transactional
    @GetMapping("detail/{id}")
    public ResponseEntity<?> getDetailProduct(@PathVariable("id") Integer foodId) {
        Food food = foodService.findById(foodId);
        List<Category> categories = foodCategoryService.getCategoryListByFoodId(foodId);
        Double averageRating = ratingService.getAverageRatingByFoodId(foodId);
        Integer totalRating = ratingService.getTotalRatingByFoodId(foodId);
        Integer totalComment = commentService.getTotalCommentByFoodId(foodId);
        Integer totalOrderQuantity = orderItemService.getTotalQuantityByFoodId(foodId);
        FoodDetailDTO foodDetail = new FoodDetailDTO(food, categories, new FoodSummaryDTO(averageRating, totalRating, totalOrderQuantity, totalComment));
        return new ResponseEntity<>(foodDetail, HttpStatus.OK);
    }
}
