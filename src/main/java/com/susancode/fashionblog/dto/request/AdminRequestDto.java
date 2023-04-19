package com.susancode.fashionblog.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
public class AdminRequestDto {
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "password is required")
    private String password;
    @NotBlank(message = "password is required")
    private String confirmPassword;
    @Email(message = "email is required")
    private String email;

}
