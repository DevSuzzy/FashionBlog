package com.susancode.fashionblog.exceptions.customexceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class AdminNotFoundException extends RuntimeException {

    protected String message;
    protected HttpStatus status;

    public AdminNotFoundException(Long adminId) {
        this.message = String.format("Admin with id: %s not found", adminId);
        this.status = HttpStatus.NOT_FOUND;
    }
    public AdminNotFoundException(String email) {
        this.message = String.format("Admin with email: %s not found", email);
        this.status = HttpStatus.NOT_FOUND;
    }

}