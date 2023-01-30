package com.example.cookingBlog.controllers;

import com.example.cookingBlog.dto.RecipeDto;
import com.example.cookingBlog.models.Comment;
import com.example.cookingBlog.models.Image;
import com.example.cookingBlog.models.Recipe;
import com.example.cookingBlog.repositories.BlogRepository;
import com.example.cookingBlog.services.BlogService;
import com.example.cookingBlog.services.CommentService;
import com.example.cookingBlog.services.ImageService;
import com.example.cookingBlog.services.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/{user_id}/blog")
public class BlogController {
    private final BlogRepository blogRepository;

    private final BlogService blogService;
    private final RecipeService recipeService;
    private final ImageService imageService;

    private final CommentService commentService;

    @GetMapping("/changeUsername")
    public String getChangeUsername(Authentication authentication, @PathVariable("user_id") String id, Model model) {
        if (authentication != null) {
            model.addAttribute("blog_id", id);
            model.addAttribute("blog_title", blogService.getBlogById(Long.valueOf(id)).getTitle());
            return "changeUsername";
        } else return "redirect:/signIn";
    }

    @PostMapping("/changeUsername")
    public String postChangeUsername(@PathVariable("user_id") String id,
                                     @RequestParam("newTitle") String newTitle, Model model) {
        boolean isOk = newTitle.chars()
                .mapToObj(Character.UnicodeBlock::of)
                .anyMatch(b -> b.equals(Character.UnicodeBlock.BASIC_LATIN));
        if (isOk) {
            blogService.changeTitle(newTitle, Long.valueOf(id));
            return "redirect:/" + id + "/blog";
        } else {
            model.addAttribute("fail", "Название должно состоять из латинских букв");
            model.addAttribute("blog_id", id);
            model.addAttribute("blog_title", blogRepository.getBlogById(Long.valueOf(id)).getTitle());
            return "changeUsername";
        }
    }

    @GetMapping("/create")
    public String getNewRecipe(Authentication authentication, @PathVariable("user_id") String id, Model model) {
        if (authentication != null) {
            model.addAttribute("blog_id", id);
            model.addAttribute("blog_title", blogService.getBlogById(Long.valueOf(id)).getTitle());
            return "newRecipe";
        } else return "redirect:/signIn";
    }

    @PostMapping("/create")
    public String postNewRecipe(@RequestParam("file") MultipartFile file,
                                @RequestParam("title") String title,
                                @RequestParam("recipe_text") String recipe_text,
                                Model model,
                                @PathVariable("user_id") String id) throws IOException {
        if (title != null && recipe_text != null && !file.isEmpty()) {
            Image image = imageService.store(file);
            RecipeDto recipeDto = new RecipeDto(title, recipe_text, image, blogService.getBlogById(Long.valueOf(id)));
            Recipe recipe = recipeService.createRecipe(recipeDto);
            imageService.setRec(recipe.getId(), image.getId());
            return "redirect:/" + id + "/blog";
        } else {
            model.addAttribute("fail", "Вы заполнили не все поля");
            model.addAttribute("blog_id", id);
            model.addAttribute("blog_title", blogRepository.getBlogById(Long.valueOf(id)).getTitle());
            return "newRecipe";
        }
    }

    @GetMapping
    public String getBlog(Authentication authentication, @PathVariable("user_id") String id, Model model) {
        if (authentication != null) {
            model.addAttribute("blog_id", id);
            List<Recipe> recipes = recipeService.getRecipesByDateByOneBlog(blogService.getBlogById(Long.valueOf(id)));
            model.addAttribute("recipes", recipes);
            model.addAttribute("blog_title", blogService.getBlogById(Long.valueOf(id)).getTitle());
            model.addAttribute("userId", id);
            return "blog";
        } else return "redirect:/signIn";
    }

    @GetMapping("/myRecipe/{recipe_id}")
    public String getMyRecipe(Authentication authentication, @PathVariable("recipe_id") String recipe_id, @PathVariable("user_id") String user_id, Model model) {
        if (authentication != null) {
            model.addAttribute("recipe_title", recipeService.getRecipeById(Long.valueOf(recipe_id)).getTitle());
            model.addAttribute("blog_title", blogService.getBlogByRecipeId(Long.valueOf(recipe_id)).getTitle());
            model.addAttribute("recipe_text", recipeService.getRecipeById(Long.valueOf(recipe_id)).getRecipe_text());
            model.addAttribute("user_id", user_id);
            List<Comment> comments = commentService.getCommentsByRecipe(Long.valueOf(recipe_id));
            model.addAttribute("comments", comments);
            return "myRecipe";
        } else return "redirect:/signIn";
    }

    @PostMapping("/myRecipe/{recipe_id}/delete")
    public String deleteRecipe(Authentication authentication, @PathVariable("user_id") String user_id, @PathVariable("recipe_id") String recipe_id) {
        if (authentication != null) {
            recipeService.deleteRecipe(Long.valueOf(recipe_id));
            return "redirect:/" + user_id + "/blog";
        } else return "redirect:/signIn";
    }

    @PostMapping("/clearBlog")
    public String clearBlog(Authentication authentication, @PathVariable String user_id) {
        if (authentication != null) {
            blogService.clearBlog(Long.valueOf(user_id));
            return "redirect:/" + user_id + "/blog";
        } else return "redirect:/signIn";
    }

    @PostMapping("/myRecipe/{recipe_id}/update")
    public String updateRecipe(@PathVariable("recipe_id") String recipe_id, @PathVariable("user_id") String user_id,
                               @RequestParam("title") String title,
                               @RequestParam("file") MultipartFile file,
                               @RequestParam("recipe_text") String recipe_text) {
        if (title != null) recipeService.setNewTitle(Long.valueOf(recipe_id), title);
        if (!file.isEmpty()) recipeService.setNewPicture(Long.valueOf(recipe_id), file);
        recipeService.updateRecipeText(Long.valueOf(recipe_id), recipe_text);
//        return "redirect:/" + user_id + "/blog/myRecipe/" + recipe_id;
        return "redirect:/" + user_id + "/blog";
    }

    @GetMapping("/myRecipe/{recipe_id}/update")
    public String updateRecipe(Authentication authentication,
                               @PathVariable("recipe_id") String recipe_id, @PathVariable("user_id") String user_id,
                               Model model) {
        if (authentication != null) {
            model.addAttribute("recipe_title", recipeService.getRecipeById(Long.valueOf(recipe_id)).getTitle());
            model.addAttribute("blog_title", blogService.getBlogById(Long.valueOf(user_id)).getTitle());
            model.addAttribute("recipe_text", recipeService.getRecipeById(Long.valueOf(recipe_id)).getRecipe_text());
            model.addAttribute("recipe_id", recipe_id);
            model.addAttribute("user_id", user_id);
            return "updateRecipe";
        } else return "redirect:/signIn";
    }
}
