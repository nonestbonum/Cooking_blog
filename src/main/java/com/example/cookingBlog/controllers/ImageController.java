package com.example.cookingBlog.controllers;

import com.example.cookingBlog.models.Image;
import com.example.cookingBlog.models.Recipe;
import com.example.cookingBlog.repositories.ImageRepository;
import com.example.cookingBlog.repositories.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.ByteArrayInputStream;

@Controller
@RequiredArgsConstructor
public class ImageController {
    private final ImageRepository imageRepository;
    private final RecipeRepository recipeRepository;

    @GetMapping("/images/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id) {
        Recipe recipe = recipeRepository.getById(id);
        Image image = imageRepository.getImageByRecipe(recipe);
        return ResponseEntity.ok()
                .body(new InputStreamResource(new ByteArrayInputStream(image.getData())));


    }
}
