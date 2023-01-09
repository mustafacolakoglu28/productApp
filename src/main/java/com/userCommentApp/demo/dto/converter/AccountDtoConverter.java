package com.userCommentApp.demo.dto.converter;

import com.userCommentApp.demo.dto.CommentAccountDto;
import com.userCommentApp.demo.dto.AccountDto;
import com.userCommentApp.demo.entity.Account;
import com.userCommentApp.demo.exception.NotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountDtoConverter {


    public AccountDto convert (Account from){

        return AccountDto.builder()
                .id(from.getId())
                .name(from.getName())
                .phoneNumber(from.getPhoneNumber())
                .email(from.getEmail())
                .surname(from.getSurname())
                .userName(from.getUserName())
                .build();
    }

    public CommentAccountDto convertToCommentAccount(Account from){
        return CommentAccountDto.builder()
                .name(from.getName())
                .surname(from.getSurname())
                .id(from.getId())
                .build();
    }
    public List<AccountDto> convertToAccountDtoList(List<Account> accounts){
        if(accounts.isEmpty()){
           throw new NotFoundException("account not found");
        }
        return accounts.stream().map(a->convert(a)).collect(Collectors.toList());
    }
}
