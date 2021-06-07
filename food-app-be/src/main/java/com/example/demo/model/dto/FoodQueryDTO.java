package com.example.demo.model.dto;

import java.util.List;

public class FoodQueryDTO {

    private Integer size;
    private Integer page;
    private List<Integer> categories;
    private List<Integer> statuses;
    private String name;
    private Double highPrice;
    private Double lowPrice;
    private Integer lowRating;
    private Integer highRating;

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Integer> getCategories() {
        return categories;
    }

    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }

    public List<Integer> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Integer> statuses) {
        this.statuses = statuses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(Double highPrice) {
        this.highPrice = highPrice;
    }

    public Double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Integer getLowRating() {
        return lowRating;
    }

    public void setLowRating(Integer lowRating) {
        this.lowRating = lowRating;
    }

    public Integer getHighRating() {
        return highRating;
    }

    public void setHighRating(Integer highRating) {
        this.highRating = highRating;
    }

}
