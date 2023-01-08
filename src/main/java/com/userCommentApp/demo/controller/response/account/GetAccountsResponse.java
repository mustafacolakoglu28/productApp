package com.userCommentApp.demo.controller.response.account;

import com.userCommentApp.demo.dto.AccountCommentDto;
import com.userCommentApp.demo.dto.AccountDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class GetAccountsResponse {
    private String id;
    private String name;
    private String surname;
    private String userName;
    private  String email;
    private String phoneNumber;
    private List<AccountCommentDto> comments;

    public static List<GetAccountsResponse> toResponse(List<AccountDto> accountDto){
        return accountDto.stream().map(a->builder()
                .surname(a.getSurname())
                .name(a.getName())
                .phoneNumber(a.getPhoneNumber())
                .id(a.getId())
                .userName(a.getUserName())
                .email(a.getEmail())
                .comments(a.getComments()).build()).collect(Collectors.toList());
    }



}
