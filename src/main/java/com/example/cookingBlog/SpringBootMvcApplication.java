package com.example.cookingBlog;

import com.example.cookingBlog.repositories.RecipeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SpringBootMvcApplication {

    public static void main(String[] args)  {
        SpringApplication.run(SpringBootMvcApplication.class, args);
    }

}
