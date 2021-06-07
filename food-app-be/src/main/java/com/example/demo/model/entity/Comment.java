package com.example.demo.model.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @Column(name = "comment_content")
    private String commentContent;

    @Column(name = "comment_image")
    private String commentImage;

    @ManyToOne
    @JoinColumn(name = "food_id", referencedColumnName = "food_id")
    private Food food;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;

    @Column(name = "comment_time")
    private LocalDate commentTime;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentImage() {
        return commentImage;
    }

    public void setCommentImage(String commentImage) {
        this.commentImage = commentImage;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public LocalDate getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(LocalDate commentTime) {
        this.commentTime = commentTime;
    }
}
