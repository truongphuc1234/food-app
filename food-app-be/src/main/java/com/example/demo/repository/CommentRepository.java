package com.example.demo.repository;

import com.example.demo.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM comment WHERE food_id = :foodId")
    Integer getTotalCommentByFoodId(Integer foodId);

    @Query(nativeQuery = true, value = "SELECT * FROM comment WHERE food_id = :foodId ORDER BY comment_time DESC ")
    Iterable<Comment> getCommentByFoodId(Integer foodId);
}
