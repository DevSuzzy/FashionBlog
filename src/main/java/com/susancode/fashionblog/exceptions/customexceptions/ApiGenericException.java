package com.susancode.fashionblog.exceptions.customexceptions;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApiGenericException extends RuntimeException{

    private String errorStatusCode;
    private String message;
    private final LocalDateTime errorTime = LocalDateTime.now();

    public ApiGenericException(String message, String errorStatusCode, String message1) {
        super(message);
        this.errorStatusCode = errorStatusCode;
        this.message = message1;
    }
}
