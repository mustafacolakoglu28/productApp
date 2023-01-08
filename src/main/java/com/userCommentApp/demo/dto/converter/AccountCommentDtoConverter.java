package com.userCommentApp.demo.dto.converter;

import com.userCommentApp.demo.dto.AccountCommentDto;
import com.userCommentApp.demo.entity.Comment;
import com.userCommentApp.demo.exception.NotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountCommentDtoConverter {

    public AccountCommentDto convert(Comment from){
        return AccountCommentDto.builder()
                .comment(from.getText())
                .commentedDate(from.getCommentedDate())
                .build();
    }
    public List<AccountCommentDto> converToAccountComment(List<Comment> comments){
        if(comments == null){
            throw new NotFoundException("no comment yet");

        }
        return comments.stream().map(this::convert).collect(Collectors.toList());
    }

}
