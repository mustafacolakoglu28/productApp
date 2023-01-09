package com.userCommentApp.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AccountCommentDto {
    private String text;
    private LocalDate commentedDate;
    private String productId;

}
