package com.userCommentApp.demo.controller.request.account;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DeleteByEmailAndPhoneNumberRequest {
    @NotBlank(message = "user name is required")
    private String userName;
    @NotBlank(message = "phone number is required")

    private String phoneNumber;
}
