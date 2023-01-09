package com.userCommentApp.demo.controller.response.comment;

import com.userCommentApp.demo.dto.CommentDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class DeleteCommentByIdResponse {
    private String id;
    private LocalDate commentedDate;
    private String productId;

    public static DeleteCommentByIdResponse toResponse(CommentDto comment){
        return DeleteCommentByIdResponse.builder()
                .commentedDate(comment.getCommentedDate())
                .id(comment.getId())
                .productId(comment.getProductId())
                .build();

    }
}
