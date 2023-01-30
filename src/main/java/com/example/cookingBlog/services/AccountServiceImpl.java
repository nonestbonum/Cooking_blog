package com.example.cookingBlog.services;

import com.example.cookingBlog.exceptions.AccountNotFoundException;
import com.example.cookingBlog.models.Account;
import com.example.cookingBlog.models.Role;
import com.example.cookingBlog.repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final BlogRepository blogRepository;
    private final AccountsRepository accountsRepository;
    private final BlogService blogService;

    private final CommentService commentService;

    private final RecipeRepository recipeRepository;

    private final ImageRepository imageRepository;

    private final CommentRepository commentRepository;

    @Override
    public List<Account> getAllAccounts() {
        return accountsRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    @Transactional
    public void deleteAccount(Long accountId) {
        accountsRepository.deleteAccountById(accountId);
    }

    @Override
    public Account getAccount(Long accountId) {
        return accountsRepository
                .findById(accountId)
                .orElseThrow(AccountNotFoundException::new);
    }

    @Override
    public void changeEmail(Long accountId, String newEmail) {
        Account accountToChange = accountsRepository.getById(accountId);
        accountToChange.setEmail(newEmail);
        accountsRepository.save(accountToChange);
    }

    @Override
    public void changeRole(Long accountId, String newRole) {
        Account accountToChange = accountsRepository.getById(accountId);
        accountToChange.setRole(Role.valueOf(newRole));
        accountsRepository.save(accountToChange);
    }

    @Override
    public void banned(Long accountId) {
        Account accountToChange = accountsRepository.getById(accountId);
        accountToChange.setBanned(true);
        accountsRepository.save(accountToChange);
    }

    @Override
    public void unBanned(Long accountId) {
        Account accountToChange = accountsRepository.getById(accountId);
        accountToChange.setBanned(false);
        accountsRepository.save(accountToChange);
    }
}
