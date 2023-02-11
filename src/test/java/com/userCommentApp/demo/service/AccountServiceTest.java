package com.userCommentApp.demo.service;

import com.userCommentApp.demo.controller.request.account.AddAccountRequest;
import com.userCommentApp.demo.controller.request.account.FindByPhoneNumberRequest;
import com.userCommentApp.demo.controller.request.account.DeleteByEmailAndPhoneNumberRequest;

import com.userCommentApp.demo.controller.request.account.UpdateAccountRequest;
import com.userCommentApp.demo.dto.AccountDto;
import com.userCommentApp.demo.dto.converter.AccountDtoConverter;
import com.userCommentApp.demo.entity.Account;
import com.userCommentApp.demo.exception.NotFoundException;
import com.userCommentApp.demo.repository.AccountRepository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 class AccountServiceTest {


    private AccountService accountService;
    private AccountDtoConverter accountDtoConverter;
    private AccountRepository accountRepository;


    @BeforeEach
     void setUp(){
        accountDtoConverter = mock(AccountDtoConverter.class);
        accountRepository = mock(AccountRepository.class);

        accountService = new AccountService(accountDtoConverter, accountRepository);
    }

    @Test
     void testAddAccount() {
        AddAccountRequest request = new AddAccountRequest();
        request.setName("name");
        request.setSurname("surname");
        request.setUserName("userName");
        request.setEmail("email");
        request.setPhoneNumber("phoneNumber");

        String uuid = UUID.randomUUID().toString();

        Account account = new Account();
        account.setName(request.getName());
        account.setSurname(request.getSurname());
        account.setUserName(request.getUserName());
        account.setEmail(request.getEmail());
        account.setPhoneNumber(request.getPhoneNumber());


        Account savedAccount = new Account(uuid,
                request.getName(),
                request.getSurname(),
                request.getUserName(),
                request.getEmail(),
                request.getPhoneNumber(),
                emptyList());

        AccountDto accountDto = new AccountDto(account.getId(),
                account.getName(),
                account.getSurname(),
                account.getUserName(),
                account.getEmail(),
                account.getPhoneNumber(),
                emptyList());



        when(accountRepository.save(account)).thenReturn(savedAccount);
        when(accountDtoConverter.convert(savedAccount)).thenReturn(accountDto);


        AccountDto result = accountService.addAccount(request);


        assertEquals(accountDto, result);

        verify(accountRepository).save(account);
        verify(accountDtoConverter).convert(savedAccount);
    }

    @Test
     void testGetAllAccounts_itShouldReturnAccountDtoList(){
        List<Account> accounts = List.of(new Account("id1", "name1", "surname1", "userName1", "email1", "phoneNumber1", emptyList()),
                new Account("id2", "name2", "surname2", "userName2", "email2", "phoneNumber2", emptyList()));

        List<AccountDto> accountDtos = List.of(new AccountDto("id1", "name1", "surname1", "userName1", "email1", "phoneNumber1", emptyList()),
                new AccountDto("id2", "name2", "surname2", "userName2", "email2", "phoneNumber2", emptyList()));

        when(accountRepository.findAll()).thenReturn(accounts);
        when(accountDtoConverter.convert(accounts.get(0))).thenReturn(accountDtos.get(0));
        when(accountDtoConverter.convert(accounts.get(1))).thenReturn(accountDtos.get(1));

        List<AccountDto> result = accountService.getAllAccounts();

        assertEquals(accountDtos, result);

        verify(accountRepository).findAll();
        verify(accountDtoConverter).convert(accounts.get(0));
        verify(accountDtoConverter).convert(accounts.get(1));
    }


    @Test
     void testGetAccountsByName_whenAccountsExist_itShouldReturnAccountDtoList(){

       List<Account> accounts = List.of(new Account("id1", "name", "surname1", "userName1", "email1", "phoneNumber1", emptyList()),
               new Account("id2", "name", "surname2", "userName2", "email2", "phoneNumber2", emptyList()),
               new Account("id3", "name1", "surname3", "userName3", "email3", "phoneNumber3", emptyList()));

       List<AccountDto> accountDtos = List.of(new AccountDto("id1", "name", "surname1", "userName1", "email1", "phoneNumber1", emptyList()),
        new AccountDto("id2", "name", "surname1", "userName1", "email1", "phoneNumber1", emptyList()),
        new AccountDto("id3", "name1", "surname1", "userName1", "email1", "phoneNumber1", emptyList()));

        when (accountRepository.findAccountsByName("name")).thenReturn(List.of(accounts.get(0), accounts.get(1)));
        when(accountDtoConverter.convert(accounts.get(0))).thenReturn(accountDtos.get(0));
        when(accountDtoConverter.convert(accounts.get(1))).thenReturn(accountDtos.get(1));

        List<AccountDto> result = accountService.getAccountsByName("name");
        List<AccountDto> acc = List.of(accountDtos.get(0), accountDtos.get(1));
        assertEquals(acc, result);

        verify(accountRepository).findAccountsByName("name");
        verify(accountDtoConverter).convert(accounts.get(0));
        verify(accountDtoConverter).convert(accounts.get(1));
    }

    @Test
     void testGetAccountsByName_whenAccountsDoNotExist_itShouldReturnEmptyAccountDtoList(){
        when(accountRepository.findAccountsByName("name")).thenReturn(emptyList());

        List<AccountDto> result = accountService.getAccountsByName("name");

        assertEquals(emptyList(), result);
        verify(accountRepository).findAccountsByName("name");


    }
    @Test
     void testGetAccountsByPhoneNumber_whenPhoneNumberDoesExist_itShouldReturnAccountDtoList(){
        FindByPhoneNumberRequest request = new FindByPhoneNumberRequest();
        request.setPhoneNumber("phoneNumber");

        List<Account> accounts = List.of(new Account("id1", "name", "surname1", "userName1", "email1", "phoneNumber", emptyList()),
                new Account("id2", "name", "surname2", "userName2", "email2", "phoneNumber", emptyList()),
                new Account("id3", "name1", "surname3", "userName3", "email3", "phoneNumber3", emptyList()));

        List<AccountDto> accountDtos = List.of(new AccountDto("id1", "name", "surname1", "userName1", "email1", "phoneNumber", emptyList()),
                new AccountDto("id2", "name", "surname1", "userName1", "email1", "phoneNumber", emptyList()),
                new AccountDto("id3", "name1", "surname1", "userName1", "email1", "phoneNumber1", emptyList()));

        when (accountRepository.findAccountsByPhoneNumber("phoneNumber")).thenReturn(List.of(accounts.get(0), accounts.get(1)));
        when(accountDtoConverter.convert(accounts.get(0))).thenReturn(accountDtos.get(0));
        when(accountDtoConverter.convert(accounts.get(1))).thenReturn(accountDtos.get(1));

        List<AccountDto> result = accountService.getAccountsByPhoneNumber(request);
        List<AccountDto> acc = List.of(accountDtos.get(0), accountDtos.get(1));

        assertEquals(acc, result);

        verify(accountRepository).findAccountsByPhoneNumber("phoneNumber");
        verify(accountDtoConverter).convert(accounts.get(0));
        verify(accountDtoConverter).convert(accounts.get(1));



    }
    @Test
     void testGetAccountsByPhoneNumber_whenPhoneNumberDoesNotExist_itShouldReturnEmptyAccountDtoList(){
        FindByPhoneNumberRequest request = new FindByPhoneNumberRequest();
        request.setPhoneNumber("phoneNumber");


        when(accountRepository.findAccountsByPhoneNumber("phoneNumber")).thenReturn(emptyList());
        List<AccountDto> result = accountService.getAccountsByPhoneNumber(request);

        assertEquals(emptyList(), result);

        verify(accountRepository).findAccountsByPhoneNumber("phoneNumber");
        verifyNoInteractions(accountDtoConverter);



    }

    @Test
     void testDeleteAccountByPhoneNumberAndEmail_whenPhoneNumberAndEmailExist_itShouldDelete(){

        DeleteByEmailAndPhoneNumberRequest request = new DeleteByEmailAndPhoneNumberRequest();
        request.setUserName("userName");
        request.setPhoneNumber("phoneNumber");

        Account account = new Account("id", "name", "surname", "userName", "email", "phoneNumber", emptyList());


        when(accountRepository.findAccountByPhoneNumberAndUserName(request.getPhoneNumber(), request.getUserName()))
                .thenReturn(account);

         accountService.deleteAccountByPhoneNumberAndEmail(request);



        verify(accountRepository).findAccountByPhoneNumberAndUserName(request.getPhoneNumber(), request.getUserName());
        verify(accountRepository).delete(account);


    }
    @Test
     void testDeleteAccountByPhoneNumberAndEmail_whenPhoneNumberAndEmailDoNotExist_itShouldThrowNotFoundException(){

        DeleteByEmailAndPhoneNumberRequest request = new DeleteByEmailAndPhoneNumberRequest();
        request.setUserName("userName");
        request.setPhoneNumber("phoneNumber");

        when(accountRepository.findAccountByPhoneNumberAndUserName(request.getPhoneNumber(), request.getUserName()))
                .thenThrow(new NotFoundException("Account not found") );


        assertThrows(NotFoundException.class, ()-> accountService.deleteAccountByPhoneNumberAndEmail(request));

        verify(accountRepository).findAccountByPhoneNumberAndUserName(request.getPhoneNumber(),request.getUserName());
        verifyNoMoreInteractions(accountRepository);


    }

    @Test
     void testUpdateAccount_whenIdExist_itShouldReturnAccountDto(){
        UpdateAccountRequest request = new UpdateAccountRequest();
        request.setUserName("userName");
        request.setSurname("surname");
        request.setName("name");

        String id = "id";

        List<Account> accounts = List.of(new Account("id", "name", "surname1", "userName", "email1", "phoneNumber", emptyList()),
                new Account("id2", "name", "surname2", "userName2", "email2", "phoneNumber2", emptyList()),
                new Account("id3", "name1", "surname3", "userName3", "email3", "phoneNumber3", emptyList()));

        AccountDto accountDto = new AccountDto("id", "name", "surname1", "userName", "email1", "phoneNumber", emptyList());

        when(accountRepository.findById(id)).thenReturn(Optional.of(accounts.get(0)));
        when(accountDtoConverter.convert(accounts.get(0))).thenReturn(accountDto);
        when(accountRepository.save(accounts.get(0))).thenReturn(accounts.get(0));

        AccountDto result = accountService.updateAccount(id, request);

        assertEquals(accountDto, result);

        verify(accountRepository).findById(id);
        verify(accountDtoConverter).convert(accounts.get(0));
        verify(accountRepository).save(accounts.get(0));
    }

    @Test
     void testUpdateAccount_whenIdDoesnotExist_itShouldThrowNotFoundException(){
        UpdateAccountRequest request = new UpdateAccountRequest();
        request.setUserName("userName");
        request.setSurname("surname");
        request.setName("name");

        String id = "id";

        when(accountRepository.findById(id)).thenThrow(new NotFoundException("Account not found"));
        assertThrows(NotFoundException.class, ()->accountService.updateAccount(id, request));


        verify(accountRepository).findById(id);
        verifyNoInteractions(accountDtoConverter);
        verifyNoMoreInteractions(accountRepository);

    }

}