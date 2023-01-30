package com.example.cookingBlog.services;

import com.example.cookingBlog.models.Comment;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentsByRecipe(Long recipeId);

    void saveComment(Comment comment);

    @Transactional
    void deleteComments(Long bloId);
}
