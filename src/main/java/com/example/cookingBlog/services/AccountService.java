package com.example.cookingBlog.services;

import com.example.cookingBlog.models.Account;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AccountService {
    List<Account> getAllAccounts();

    @Transactional
    void deleteAccount(Long accountId);

    void banned(Long accountId);

    void unBanned(Long accountId);

    public void changeAccount(String accountEmail, String accountRole, String account_blogName, String account_id);

}
