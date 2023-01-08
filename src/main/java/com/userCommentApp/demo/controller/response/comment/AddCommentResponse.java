package com.userCommentApp.demo.controller.response.comment;

import com.userCommentApp.demo.dto.CommentAccountDto;
import com.userCommentApp.demo.dto.CommentDto;
import com.userCommentApp.demo.entity.Account;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AddCommentResponse {
    private String text;
    private CommentAccountDto account;
    private LocalDate commentedDate;
    private String productId;

    public static AddCommentResponse toResponse(CommentDto commentDto){
        return  AddCommentResponse.builder()
                .text(commentDto.getText())
                .commentedDate(commentDto.getCommentedDate())
                .account(commentDto.getAccount())
                .productId(commentDto.getProductId())
                .build();
    }

}
