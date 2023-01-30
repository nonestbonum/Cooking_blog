package com.example.cookingBlog.controllers;

import com.example.cookingBlog.dto.SignUpForm;
import com.example.cookingBlog.services.SignUpService;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/signUp")
public class SignUpController {

    private final SignUpService signUpService;

    @GetMapping
    public String getSignUpPage(Model model) {
        model.addAttribute("signUpForm", new SignUpForm());
        return "signUp";
    }

    @PostMapping
    public String signUp(@Valid SignUpForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("signUpForm", new SignUpForm());
            model.addAttribute("fail", "Введены некорректные данные");
            return "signUp";
        } else {
            signUpService.signUp(form);
            return "redirect:/signIn";
        }
    }


}






