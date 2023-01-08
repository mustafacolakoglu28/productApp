package com.userCommentApp.demo.controller.response.account;

import com.userCommentApp.demo.dto.AccountDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateAccountResponse {
    private String name;
    private String surname;
    private String userName;

    public static UpdateAccountResponse toResponse(AccountDto accountDto){
        return UpdateAccountResponse.builder()
                .name(accountDto.getName())
                .surname(accountDto.getSurname())
                .userName(accountDto.getUserName())
                .build();
    }

}
