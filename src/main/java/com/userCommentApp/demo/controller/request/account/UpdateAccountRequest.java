package com.userCommentApp.demo.controller.request.account;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateAccountRequest {

    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "name is required")
    private String surname;
    @NotBlank(message = "name is required")
    private String userName;

}
