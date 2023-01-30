package com.example.cookingBlog.repositories;


import com.example.cookingBlog.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Long> {

    boolean existsAccountByEmailAndPassword(String email, String pass);

    Account getAccountByEmailAndPassword(String email, String pass);

    Optional<Account> findByEmail(String email);

    @Transactional
    void deleteAccountById(Long accId);


//    @Query(nativeQuery = true, value = "select * from account_table")
//    List<Account> getAllAccounts();


}
