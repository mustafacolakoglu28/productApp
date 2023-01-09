package com.userCommentApp.demo.controller.response.comment;

import com.userCommentApp.demo.dto.AccountCommentDto;
import com.userCommentApp.demo.exception.NotFoundException;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class GetCommentsByUserResponse {
    private String text;
    private LocalDate commentedDate;
    private String productId;

    public static List<GetCommentsByUserResponse> toResponse(List<AccountCommentDto> accountCommentDto){
       return accountCommentDto.stream().map(a-> GetCommentsByUserResponse.builder()
                .text(a.getText())
                .commentedDate(a.getCommentedDate())
                .productId(a.getProductId())
                .build()).collect(Collectors.toList());
    }
}
