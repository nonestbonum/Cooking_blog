package com.example.cookingBlog.repositories;

import com.example.cookingBlog.models.Blog;
import com.example.cookingBlog.models.Image;
import com.example.cookingBlog.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Image getImageByRecipe(Recipe recipe);

    void deleteImageByRecipeId(Long recipeId);
}
