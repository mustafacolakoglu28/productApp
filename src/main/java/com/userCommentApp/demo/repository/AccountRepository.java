package com.userCommentApp.demo.repository;

import com.userCommentApp.demo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {


     List<Account> findAccountsByName(String name);
     List<Account> findAccountsByPhoneNumber(String phoneNumber);

     Account findAccountByPhoneNumberAndUserName(String phoneNumber, String userName);

}
