package com.example.cookingBlog.services;

import com.example.cookingBlog.dto.SignUpForm;
import com.example.cookingBlog.models.Account;
import com.example.cookingBlog.models.Blog;
import com.example.cookingBlog.models.Role;
import com.example.cookingBlog.repositories.AccountsRepository;
import com.example.cookingBlog.repositories.BlogRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final AccountsRepository accountsRepository;
    private final PasswordEncoder passwordEncoder;

    private final BlogRepository blogRepository;


    @Override
    public void signUp(SignUpForm form) {
        Account account = Account.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .email(form.getEmail().toLowerCase(Locale.ROOT))
                .role(Role.USER)
                .password(passwordEncoder.encode(form.getPassword()))
                .build();

        accountsRepository.save(account);

        Blog blog = Blog.builder()
                .id(account.getId())
                .account(account)
                .title(account.getFirstName())
                .build();

        account.setBlog(blog);

        blogRepository.save(blog);
    }
}
