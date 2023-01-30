package com.example.cookingBlog.services;

import com.example.cookingBlog.dto.RecipeDto;
import com.example.cookingBlog.models.Blog;
import com.example.cookingBlog.models.Recipe;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RecipeService {


    Recipe createRecipe(RecipeDto recipeDto);


    List<Recipe> top6();

    void deleteRecipe(Long recipeId);

    void vote(Long recipeId, int scores);

    Recipe getRecipeById(Long id);

    List<Recipe> searchRecipe(String string);

    List<Recipe> getRecipesByDateByOneBlog(Blog blog);

    void setNewTitle(Long recId, String title);

    void setNewPicture(Long recId, MultipartFile file);

    void updateRecipeText(Long recId, String recipeText);

}
