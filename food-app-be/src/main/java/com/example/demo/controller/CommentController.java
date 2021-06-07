package com.example.demo.controller;


import com.example.demo.model.entity.Comment;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/comment")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> getCommentByFoodId(@PathVariable("id") Integer foodId){
        return new ResponseEntity<>(commentService.getCommentByFoodId(foodId), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveComment(@RequestBody Comment comment) {
        commentService.saveComment(comment);
        return new ResponseEntity<>(commentService.getCommentByFoodId(comment.getFood().getFoodId()), HttpStatus.OK);
    }
}

