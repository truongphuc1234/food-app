package com.example.demo.service;

import com.example.demo.model.entity.Comment;

public interface CommentService {
    Integer getTotalCommentByFoodId(Integer foodId);

    Iterable<Comment> getCommentByFoodId(Integer foodId);

    void saveComment(Comment comment);
}
