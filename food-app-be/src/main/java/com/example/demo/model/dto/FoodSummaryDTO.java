package com.example.demo.model.dto;

public class FoodSummaryDTO {

    private Double ratingAverage;
    private Integer totalRating;
    private Integer totalOrder;
    private Integer totalComment;

    public FoodSummaryDTO(Double ratingAverage, Integer totalRating, Integer totalOrder, Integer totalComment) {
        this.ratingAverage = ratingAverage;
        this.totalRating = totalRating;
        this.totalOrder = totalOrder;
        this.totalComment = totalComment;
    }

    public Double getRatingAverage() {
        return ratingAverage;
    }

    public void setRatingAverage(Double ratingAverage) {
        this.ratingAverage = ratingAverage;
    }

    public Integer getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(Integer totalRating) {
        this.totalRating = totalRating;
    }

    public Integer getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(Integer totalOrder) {
        this.totalOrder = totalOrder;
    }

    public Integer getTotalComment() {
        return totalComment;
    }

    public void setTotalComment(Integer totalComment) {
        this.totalComment = totalComment;
    }
}
