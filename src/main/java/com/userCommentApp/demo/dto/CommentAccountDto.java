package com.userCommentApp.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentAccountDto {
    private String name;
    private String surname;
    private String id;

}
