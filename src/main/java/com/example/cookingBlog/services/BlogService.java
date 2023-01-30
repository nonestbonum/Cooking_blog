package com.example.cookingBlog.services;

import com.example.cookingBlog.models.Blog;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BlogService {
    @Transactional
    void clearBlog(Long blogId);

//    void addRecipe(Long blogId, Long recipeId);

    void changeTitle(String newTitle, Long blogId);

    Blog getBlogById(Long id);

    Blog getBlogByRecipeId(Long recipeId);
//    @Transactional
//    void deleteById(Long blogId);
}
