package com.example.cookingBlog.services;

import com.example.cookingBlog.dto.SignInForm;
import com.example.cookingBlog.repositories.AccountsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInServiceImpl implements SignInService {

    private final AccountsRepository accountsRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean signIn(SignInForm signInForm) {
        return accountsRepository.existsAccountByEmailAndPassword(signInForm.getEmail(),passwordEncoder.encode(signInForm.getPassword()));
    }
}
