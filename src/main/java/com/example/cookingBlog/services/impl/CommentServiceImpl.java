package com.example.cookingBlog.services.impl;

import com.example.cookingBlog.models.Comment;
import com.example.cookingBlog.repositories.CommentRepository;
import com.example.cookingBlog.services.BlogService;
import com.example.cookingBlog.services.CommentService;
import com.example.cookingBlog.services.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BlogService blogService;
    private final RecipeService recipeService;

    @Override
    public List<Comment> getCommentsByRecipe(Long recipeId) {
        return commentRepository.findAllByRecipeIdOrderByDateOfCreateDesc(recipeId);
    }

    @Override
    public void postNewComment(String recipe_id, String user_id, String comment_text) {

        Comment comment = new Comment(blogService.getBlogById(Long.valueOf(user_id)),
                comment_text,
                recipeService.getRecipeById(Long.valueOf(recipe_id)));
        commentRepository.save(comment);
        commentRepository.save(comment);
    }
}
