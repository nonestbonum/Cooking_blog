package com.example.cookingBlog.controllers;

import com.example.cookingBlog.models.Recipe;
import com.example.cookingBlog.repositories.RecipeRepository;
import com.example.cookingBlog.services.AccountService;
import com.example.cookingBlog.services.BlogService;
import com.example.cookingBlog.services.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class StartPageController {

    private final RecipeService recipeService;
    private final BlogService blogService;

    private final AccountService accountService;

    @GetMapping("/")
    public String getPage(Model model) {
        List<Recipe> recipes = recipeService.top6(); // топ-6 рецептов по рейтингу
        model.addAttribute("recipes", recipes);
        return "startPage";
    }

    @GetMapping("/oneRecipe/{recipe_id}")
    public String getOneRecipe(@PathVariable String recipe_id, Model model) {
        model.addAttribute("recipe_title", recipeService.getRecipeById(Long.valueOf(recipe_id)).getTitle());
        model.addAttribute("blog_title", blogService.getBlogByRecipeId(Long.valueOf(recipe_id)).getTitle());
        model.addAttribute("recipe_text", recipeService.getRecipeById(Long.valueOf(recipe_id)).getRecipe_text());
        return "oneRecipe";
    }

    @GetMapping("/search")
    public String getSearch() {
        return "/searchPage";
    }


    @PostMapping("/search")
    public String postSearch(@RequestParam("search") String searchWord, Model model) {
        model.addAttribute("recipesS", recipeService.searchRecipe(searchWord));
        model.addAttribute("searchWord", searchWord);
        return "/searchPage";
    }
}
