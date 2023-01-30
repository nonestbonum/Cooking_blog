package com.example.cookingBlog.controllers;

import com.example.cookingBlog.models.Comment;
import com.example.cookingBlog.models.Recipe;
import com.example.cookingBlog.services.BlogService;
import com.example.cookingBlog.services.CommentService;
import com.example.cookingBlog.services.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/{user-id}")
public class StartPageSignInController {
    private final RecipeService recipeService;
    private final BlogService blogService;
    private final CommentService commentService;

    @GetMapping()
    public String getStartPageSignIn(Authentication authentication, @PathVariable("user-id") String id, Model model) {
        if (authentication != null) {
            model.addAttribute("userId", id);
            List<Recipe> recipes = recipeService.top6();
            model.addAttribute("recipes", recipes);
            return "startPageSignIn";
        } else return "redirect:/signIn";
    }

    @GetMapping("/oneRecipe/{recipe_id}")
    public String getOneRecipeSignIn(Authentication authentication, @PathVariable("recipe_id") String recipe_id, @PathVariable("user-id") String user_id, Model model) {
        if (authentication != null) {
            if (recipeService.getRecipeById(Long.valueOf(recipe_id)).getBlog().equals(blogService.getBlogById(Long.valueOf(user_id)))) {
                model.addAttribute("recipe_title", recipeService.getRecipeById(Long.valueOf(recipe_id)).getTitle());
                model.addAttribute("blog_title", blogService.getBlogByRecipeId(Long.valueOf(recipe_id)).getTitle());
                model.addAttribute("recipe_text", recipeService.getRecipeById(Long.valueOf(recipe_id)).getRecipe_text());
                model.addAttribute("user_id", user_id);
                model.addAttribute("recipe_id", recipe_id);
                List<Comment> comments = commentService.getCommentsByRecipe(Long.valueOf(recipe_id));
                model.addAttribute("comments", comments);
                return "myRecipe";
            } else {
                model.addAttribute("recipe_title", recipeService.getRecipeById(Long.valueOf(recipe_id)).getTitle());
                model.addAttribute("blog_title", blogService.getBlogByRecipeId(Long.valueOf(recipe_id)).getTitle());
                model.addAttribute("recipe_text", recipeService.getRecipeById(Long.valueOf(recipe_id)).getRecipe_text());
                model.addAttribute("user_id", user_id);
                List<Comment> comments = commentService.getCommentsByRecipe(Long.valueOf(recipe_id));
                model.addAttribute("comments", comments);
                return "oneRecipeSignIn";
            }
        } else return "redirect:/signIn";
    }

    @PostMapping("/oneRecipe/{recipe_id}")
    public String postOneRecipe(@PathVariable String recipe_id, @RequestParam("rating") String value, @PathVariable("user-id") String user_id) {
        recipeService.vote(Long.valueOf(recipe_id), Integer.parseInt(value));
        return "redirect:/" + user_id + "/oneRecipe/" + recipe_id;
    }

    @GetMapping("/search")
    public String getSearch(@PathVariable("user-id") String parameter, Authentication authentication) {
        if (authentication != null) {
            return "/searchPageSignIn";
        } else return "redirect:/signIn";
    }


    @PostMapping("/search")
    public String postSearch(@RequestParam("search") String searchWord, Model model, @PathVariable("user-id") String parameter) {
        model.addAttribute("userId", parameter);
        model.addAttribute("recipesS", recipeService.searchRecipe(searchWord));
        model.addAttribute("searchWord", searchWord);
        return "/searchPageSignIn";
    }

    @PostMapping("/oneRecipe/{recipe_id}/comment")
    public String postComment(@PathVariable("recipe_id") String recipe_id,
                              @PathVariable("user-id") String user_id,
                              Model model,
                              @RequestParam("comment_text") String comment_text) {
        Comment comment = new Comment(blogService.getBlogById(Long.valueOf(user_id)),
                comment_text,
                recipeService.getRecipeById(Long.valueOf(recipe_id)));
        commentService.saveComment(comment);
        return "redirect:/" + user_id + "/oneRecipe/" + recipe_id;
    }
}
