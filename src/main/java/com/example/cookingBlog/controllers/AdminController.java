package com.example.cookingBlog.controllers;

import com.example.cookingBlog.models.Recipe;
import com.example.cookingBlog.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AdminController {
    private final BlogService blogService;

    private final AccountService accountService;


    private final CommentService commentService;

    private final RecipeService recipeService;


    @GetMapping()
    public String getAdminAccounts(Authentication authentication, Model model) {
        if (authentication != null) {
            model.addAttribute("accounts", accountService.getAllAccounts());
            return "adminPageAccounts";
        } else return "redirect:/signIn";
    }

    @PostMapping("/{account_id}/change")
    public String changeAccount(@RequestParam("accountEmail") String accountEmail,
                                @RequestParam("accountRole") String accountRole,
                                @RequestParam("account_blogName") String account_blogName, @PathVariable("account_id") String account_id) {
        accountService.changeEmail(Long.valueOf(account_id), accountEmail);
        accountService.changeRole(Long.valueOf(account_id), accountRole);
        blogService.changeTitle(account_blogName, Long.valueOf(account_id));
        return "redirect:/accounts";
    }

    @PostMapping("/{account_id}/ban")
    public String banAccount(@PathVariable String account_id) {
        accountService.banned(Long.valueOf(account_id));
        return "redirect:/accounts";
    }

    @PostMapping("/{account_id}/unBan")
    public String unBanAccount(@PathVariable String account_id) {
        accountService.unBanned(Long.valueOf(account_id));
        return "redirect:/accounts";
    }

    @PostMapping("/{account_id}/delete")
    public String deleteAccount(@PathVariable("account_id") String account_id) {
        commentService.deleteComments(Long.valueOf(account_id));
        blogService.clearBlog(Long.valueOf(account_id));
        accountService.deleteAccount(Long.valueOf(account_id));
        return "redirect:/accounts";
    }

    @GetMapping("{account_id}/posts")
    public String getPosts(@PathVariable("account_id") String account_id,
                           Model model) {
        List<Recipe> recipes = recipeService.getRecipesByDateByOneBlog(blogService.getBlogById(Long.valueOf(account_id)));
        model.addAttribute("recipes", recipes);
        model.addAttribute("title", blogService.getBlogById(Long.valueOf(account_id)).getTitle());
        return "adminPagePosts";
    }

    @PostMapping("/{account_id}/{recipe_id}/changePost")
    public String changePost(@PathVariable("recipe_id") String recipe_id,
                             @RequestParam("photo") MultipartFile file,
                             @RequestParam("title") String title, @PathVariable("account_id") String account_id) {
        recipeService.setNewTitle(Long.valueOf(recipe_id), title);
        if (!file.isEmpty()) recipeService.setNewPicture(Long.valueOf(recipe_id), file);
        return "redirect:/accounts/" + account_id + "/posts";
    }
}
