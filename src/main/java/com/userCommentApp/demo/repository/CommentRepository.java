package com.userCommentApp.demo.repository;

import com.userCommentApp.demo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, String> {

    List<Comment> getCommentsByProductId(String id);
}
