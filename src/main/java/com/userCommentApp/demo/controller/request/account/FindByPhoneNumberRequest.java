package com.userCommentApp.demo.controller.request.account;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FindByPhoneNumberRequest {
    @NotBlank(message = "phone number is required")
    private String phoneNumber;

}
