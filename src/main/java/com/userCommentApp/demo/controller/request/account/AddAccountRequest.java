package com.userCommentApp.demo.controller.request.account;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AddAccountRequest {
    @NotBlank(message = "name is required")
    @Max(50)
    private String name;
    @NotBlank(message = "surname is required")
    @Max(50)
    private String surname;
    @NotBlank(message = "user name is required")
    @Max(50)
    private String userName;
    @NotBlank(message = "email is required")
    @Max(20)
    private String email;
    @NotBlank(message = "phone number is required")
    @Max(15)
    private String phoneNumber;
}
