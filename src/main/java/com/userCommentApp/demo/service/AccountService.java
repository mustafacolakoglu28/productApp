package com.userCommentApp.demo.service;

import com.userCommentApp.demo.controller.request.account.AddAccountRequest;
import com.userCommentApp.demo.controller.request.account.DeleteByEmailAndPhoneNumberRequest;
import com.userCommentApp.demo.controller.request.account.FindByPhoneNumberRequest;
import com.userCommentApp.demo.controller.request.account.UpdateAccountRequest;
import com.userCommentApp.demo.dto.AccountDto;
import com.userCommentApp.demo.dto.converter.AccountDtoConverter;
import com.userCommentApp.demo.entity.Account;
import com.userCommentApp.demo.exception.NotFoundException;
import com.userCommentApp.demo.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountDtoConverter accountDtoConverter;

    private final AccountRepository accountRepository;

    public AccountService(AccountDtoConverter accountDtoConverter, AccountRepository accountRepository) {
        this.accountDtoConverter = accountDtoConverter;
        this.accountRepository = accountRepository;
    }

    public Account findAccountById(String id){
        return accountRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Account not found"));
    }

    public AccountDto addAccount(AddAccountRequest request) {
        Account account = new Account();
        account.setUserName(request.getUserName());
        account.setName(request.getName());
        account.setSurname(request.getSurname());
        account.setEmail(request.getEmail());
        account.setPhoneNumber(request.getPhoneNumber());

        return accountDtoConverter.convert(accountRepository.save(account));

    }

    public List<AccountDto> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(accountDtoConverter::convert)
                .collect(Collectors.toList());
    }

    public List<AccountDto> getAccountsByName(String name) {

        return accountRepository.findAccountsByName(name)
                .stream()
                .map(accountDtoConverter::convert)
                .collect(Collectors.toList());
    }

    public List<AccountDto> getAccountsByPhoneNumber(FindByPhoneNumberRequest request) {
        return accountRepository.findAccountsByPhoneNumber(request.getPhoneNumber())
                .stream().map(accountDtoConverter::convert).collect(Collectors.toList());
    }

    public AccountDto deleteAccountByPhoneNumberAndEmail(DeleteByEmailAndPhoneNumberRequest request) {
        Account account = accountRepository
                .findAccountByPhoneNumberAndUserName(request.getPhoneNumber(), request.getUserName());

        if (account == null) {

             throw new NotFoundException("Phone number and username do not match any user");

        }
        accountRepository.delete(account);
        return accountDtoConverter.convert(account);
    }

    public AccountDto updateAccount(String id, UpdateAccountRequest request) {
        Account account = findAccountById(id);
        account.setName(request.getName());
        account.setUserName(request.getUserName());
        account.setSurname(request.getSurname());

        return accountDtoConverter.convert(accountRepository.save(account));
    }

}
