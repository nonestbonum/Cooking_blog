package com.example.cookingBlog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorsController {

    @GetMapping("/error/404")
    public String get404Page() {
        return "errors/error_404";
    }
}
