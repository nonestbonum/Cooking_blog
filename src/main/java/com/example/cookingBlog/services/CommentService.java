package com.example.cookingBlog.services;

import com.example.cookingBlog.models.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentsByRecipe(Long recipeId);

    void postNewComment(String recipe_id, String user_id, String comment_text);
}
