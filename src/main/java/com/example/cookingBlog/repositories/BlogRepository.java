package com.example.cookingBlog.repositories;

import com.example.cookingBlog.models.Account;
import com.example.cookingBlog.models.Blog;
import com.example.cookingBlog.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {

    Optional<Blog> findByAccount(Account account);

    Blog getBlogById(Long id);

    void deleteBlogById(Long aLong);
}
