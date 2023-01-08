package com.userCommentApp.demo.controller.response.account;

import com.userCommentApp.demo.dto.AccountDto;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddAccountResponse {
    private String id;

    public static AddAccountResponse toResponse(AccountDto accountDto){
        return AddAccountResponse.builder()
                .id(accountDto.getId())
                .build();


    }
}
