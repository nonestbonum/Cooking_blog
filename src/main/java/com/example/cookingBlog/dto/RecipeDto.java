package com.example.cookingBlog.dto;

import com.example.cookingBlog.models.Blog;
import com.example.cookingBlog.models.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecipeDto {
    private String title;
    private String recipe_text;
    private Image image;
    private Blog blog;

}
