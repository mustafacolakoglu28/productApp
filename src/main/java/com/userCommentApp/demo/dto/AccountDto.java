package com.userCommentApp.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AccountDto {
    private String id;
    private String name;
    private String surname;
    private String userName;
    private  String email;
    private String phoneNumber;
    private List<AccountCommentDto> comments;
}
