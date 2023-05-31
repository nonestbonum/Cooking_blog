package com.example.cookingBlog.services.impl;

import com.example.cookingBlog.dto.RecipeDto;
import com.example.cookingBlog.models.Blog;
import com.example.cookingBlog.models.Image;
import com.example.cookingBlog.models.Recipe;
import com.example.cookingBlog.repositories.ImageRepository;
import com.example.cookingBlog.repositories.RecipeRepository;
import com.example.cookingBlog.services.BlogService;
import com.example.cookingBlog.services.ImageService;
import com.example.cookingBlog.services.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;


@RequiredArgsConstructor
@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {

    private final ImageRepository imageRepository;
    private final RecipeRepository recipeRepository;
    private final ImageService imageService;
    private final BlogService blogService;

    @Override
    public void postNewRecipe(MultipartFile file, String title, String recipe_text, String id) throws IOException {

        Image image = imageService.store(file);
        RecipeDto recipeDto = new RecipeDto(title, recipe_text, image, blogService.getBlogById(Long.valueOf(id)));
        Recipe recipe = Recipe.builder()
                .recipe_text(recipeDto.getRecipe_text())
                .image(recipeDto.getImage())
                .title(recipeDto.getTitle())
                .blog(recipeDto.getBlog())
                .build();
        recipeRepository.save(recipe);
        imageService.setRec(recipe.getId(), image.getId());
    }

    @Override
    public boolean isValidUsername(String newTitle) {

        return newTitle.chars()
                .mapToObj(Character.UnicodeBlock::of)
                .anyMatch(b -> b.equals(Character.UnicodeBlock.BASIC_LATIN));
    }

    @Override
    public List<Recipe> top6() {

        return recipeRepository.top6();
    }


    @Override
    @Transactional
    public void deleteRecipe(Long recipeId) {

        recipeRepository.deleteRecipeById(recipeId);
    }

    @Override
    public void vote(Long recipeId, int scores) {

        Recipe recipe = recipeRepository.getById(recipeId);
        double countOfVotes = recipe.getCountOfVotes();
        countOfVotes += 1;
        recipe.setCountOfVotes(countOfVotes);

        double rScores = recipe.getScores();
        rScores += scores;
        recipe.setScores(rScores);

        recipe.setRating(rScores / countOfVotes);
        recipeRepository.save(recipe);
    }

    public Recipe getRecipeById(Long id) {

        return recipeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Recipe> searchRecipe(String string) {

        return recipeRepository.findAllByKeyword(string);
    }

    @Override
    public List<Recipe> getRecipesByDateByOneBlog(Blog blog) {

        return recipeRepository.findAllByBlogOrderByDateOfCreateDesc(blog);
    }

    @Override
    public void setNewTitle(Long recId, String title) {

        Recipe recipe = recipeRepository.getById(recId);
        recipe.setTitle(title);
        recipeRepository.save(recipe);
    }

    @SneakyThrows
    @Override
    @Transactional
    public void setNewPicture(Long recId, MultipartFile file) {

        imageRepository.deleteImageByRecipeId(recId);
        Image image = imageService.store(file);
        imageService.setRec(recId, image.getId());
    }

    @Override
    public void updateRecipeText(Long recId, String recipeText) {

        Recipe recipe = recipeRepository.getById(recId);
        recipe.setRecipe_text(recipeText);
        recipeRepository.save(recipe);
    }

    @Override
    public void changePost(String recipe_id, MultipartFile file, String title, String account_id) {

        setNewTitle(Long.valueOf(recipe_id), title);
        if (!file.isEmpty()) setNewPicture(Long.valueOf(recipe_id), file);
    }
}
