package com.example.cookingBlog.services;

import com.example.cookingBlog.models.Blog;
import com.example.cookingBlog.models.Recipe;
import com.example.cookingBlog.repositories.BlogRepository;
import com.example.cookingBlog.repositories.ImageRepository;
import com.example.cookingBlog.repositories.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final RecipeRepository recipeRepository;
    private final ImageRepository imageRepository;


    @Override
    @Transactional
    public void clearBlog(Long blogId) {
        List<Long> recipesIdList = recipeRepository.getAllRecipesIdFromOneBlog(blogId);
        for (Long l : recipesIdList) {
            imageRepository.deleteImageByRecipeId(l);
            recipeRepository.deleteRecipeById(l);
        }
    }

//    @Override
//    public void addRecipe(Long blogId, Long recipeId) {
//        blogRepository.getById(blogId).getRecipesList()
//                .add(recipeRepository.getById(recipeId));
//    }

    @Override
    @Transactional
    public void changeTitle(String newTitle, Long blogId) {
        Blog blogToChange = blogRepository.getById(blogId);
        blogToChange.setTitle(newTitle);
        blogRepository.save(blogToChange);
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogRepository.getBlogById(id);
    }

    @Override
    public Blog getBlogByRecipeId(Long recipeId) {
        return recipeRepository.getById(recipeId).getBlog();

    }
}
