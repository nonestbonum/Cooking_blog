package com.example.cookingBlog.security.details;

import com.example.cookingBlog.models.Account;
import com.example.cookingBlog.repositories.AccountsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountDetailsServiceImpl implements UserDetailsService {

    private final AccountsRepository accountsRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountsRepository.findByEmail(email).orElseThrow(()->
                new UsernameNotFoundException("Account not found"));
        return new AccountDetailsImpl(account);
    }
}
