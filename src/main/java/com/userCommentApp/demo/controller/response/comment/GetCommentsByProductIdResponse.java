package com.userCommentApp.demo.controller.response.comment;

import com.userCommentApp.demo.dto.AccountCommentDto;
import com.userCommentApp.demo.dto.CommentDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class GetCommentsByProductIdResponse {
    private String text;
    private LocalDate commentedDate;
    private String productId;

    public static List<GetCommentsByProductIdResponse> toResponse(List<CommentDto> commentDtos){
        return commentDtos.stream().map(a-> GetCommentsByProductIdResponse.builder()
                .text(a.getText())
                .commentedDate(a.getCommentedDate())
                .productId(a.getProductId())
                .build()).collect(Collectors.toList());
    }
}
