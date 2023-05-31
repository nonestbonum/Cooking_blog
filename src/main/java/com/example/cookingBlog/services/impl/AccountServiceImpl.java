package com.example.cookingBlog.services.impl;

import com.example.cookingBlog.exceptions.AccountNotFoundException;
import com.example.cookingBlog.models.Account;
import com.example.cookingBlog.models.Role;
import com.example.cookingBlog.repositories.*;
import com.example.cookingBlog.services.AccountService;
import com.example.cookingBlog.services.BlogService;
import com.example.cookingBlog.services.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {


    private final AccountsRepository accountsRepository;
    private final BlogService blogService;


    @Override
    public void changeAccount(String accountEmail,
                              String accountRole,
                              String account_blogName,
                              String account_id) {

        changeEmailAndRole(Long.valueOf(account_id), accountEmail, accountRole);
        blogService.changeTitle(account_blogName, Long.valueOf(account_id));
    }

    @Override
    public List<Account> getAllAccounts() {

        return accountsRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    @Transactional
    public void deleteAccount(Long accountId) {

        accountsRepository.deleteAccountById(accountId);
    }

    private void changeEmailAndRole(Long accountId, String newEmail, String accountRole) {

        Account accountToChange = accountsRepository.getById(accountId);
        accountToChange.setEmail(newEmail);
        accountToChange.setRole(Role.valueOf(accountRole));
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
