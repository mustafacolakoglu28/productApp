package com.userCommentApp.demo.dto.converter;

import com.userCommentApp.demo.dto.CommentDto;
import com.userCommentApp.demo.entity.Comment;
import org.springframework.stereotype.Component;


@Component
public class CommentDtoConverter {
    private final AccountDtoConverter accountDtoConverter;

    public CommentDtoConverter(AccountDtoConverter accountDtoConverter, AccountCommentDtoConverter accountCommentDtoConverter) {
        this.accountDtoConverter = accountDtoConverter;
    }

    public CommentDto convert(Comment from){
        return CommentDto.builder()
                .commentedDate(from.getCommentedDate())
                .text(from.getText())
                .account(accountDtoConverter.convertToCommentAccount(from.getAccount()))
                .productId(from.getProductId())
                .build();
    }



}
