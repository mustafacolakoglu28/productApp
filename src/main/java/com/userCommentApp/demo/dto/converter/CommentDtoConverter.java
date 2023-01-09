package com.userCommentApp.demo.dto.converter;

import com.userCommentApp.demo.dto.CommentDto;
import com.userCommentApp.demo.entity.Comment;
import com.userCommentApp.demo.exception.NotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class CommentDtoConverter {
    private final AccountDtoConverter accountDtoConverter;

    public CommentDtoConverter(AccountDtoConverter accountDtoConverter) {
        this.accountDtoConverter = accountDtoConverter;
    }

    public CommentDto convert(Comment from){
        return CommentDto.builder()
                .id(from.getId())
                .commentedDate(from.getCommentedDate())
                .text(from.getText())
                .account(accountDtoConverter.convertToCommentAccount(from.getAccount()))
                .productId(from.getProductId())
                .build();
    }

    public List<CommentDto> convertToCommentList(List<Comment> comments, String id){
        if(comments.isEmpty()){
            throw new NotFoundException(id + " has no comment yet");

        }
        return comments.stream().map(this::convert).collect(Collectors.toList());
    }
    }



