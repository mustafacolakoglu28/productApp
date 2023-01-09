package com.userCommentApp.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CommentDto {
    private String id;
    private String text;
    private LocalDate commentedDate;
    private CommentAccountDto account;
    private String productId;
}
