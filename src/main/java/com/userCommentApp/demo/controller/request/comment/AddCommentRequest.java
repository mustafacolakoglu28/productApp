package com.userCommentApp.demo.controller.request.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


@Data
public class AddCommentRequest {
    @NotBlank(message = "text is empty")
    @Length(min = 5, max = 250, message = "text must be 5-250 character")
    private String text;

    private String productId;

}
