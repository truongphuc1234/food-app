package com.example.demo.service.impl;

import com.example.demo.model.entity.Comment;
import com.example.demo.repository.CommentRepository;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    @Autowired
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Integer getTotalCommentByFoodId(Integer foodId) {
        return commentRepository.getTotalCommentByFoodId(foodId);
    }

    @Override
    public Iterable<Comment> getCommentByFoodId(Integer foodId) {
        return commentRepository.getCommentByFoodId(foodId);
    }

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }
}
