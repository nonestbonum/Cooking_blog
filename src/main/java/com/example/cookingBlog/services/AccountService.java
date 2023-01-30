package com.example.cookingBlog.services;

import com.example.cookingBlog.models.Account;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AccountService {
    List<Account> getAllAccounts();

    @Transactional
    void deleteAccount(Long accountId);

    Account getAccount(Long accountId);

    void changeEmail(Long accountId, String newEmail);

    void changeRole(Long accountId, String newRole);

    void banned(Long accountId);

    void unBanned(Long accountId);

}
