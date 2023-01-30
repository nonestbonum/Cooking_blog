package com.example.cookingBlog.repositories;

import com.example.cookingBlog.models.Blog;
import com.example.cookingBlog.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query(value = "select r from Recipe r where lower(r.title) like lower(concat('%',?1,'%')) or lower(r.recipe_text) like lower(concat('%',?1,'%')) order by r.rating desc")
    List<Recipe> findAllByKeyword(String string);

    @Query(nativeQuery = true, value = "select * from recipes_table r order by r.rating desc limit 6")
    List<Recipe> top6();

    List<Recipe> findAllByBlogOrderByDateOfCreateDesc(Blog blog);

    void deleteRecipeById(Long recipeId);

    @Modifying
    @Query(nativeQuery = true, value = "select r.recipe_id from recipes_table r where r.blog_id = ?1")
    List<Long> getAllRecipesIdFromOneBlog(Long blogId);
}
