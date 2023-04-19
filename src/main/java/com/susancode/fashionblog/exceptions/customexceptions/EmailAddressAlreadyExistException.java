package com.susancode.fashionblog.exceptions.customexceptions;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Getter
public class EmailAddressAlreadyExistException extends RuntimeException {

    protected String message;
    protected HttpStatus status;

    public EmailAddressAlreadyExistException(String message1) {
        this.message = String.format("User with email %s already exist", message1);
        this.status = HttpStatus.CONFLICT;
    }


}





