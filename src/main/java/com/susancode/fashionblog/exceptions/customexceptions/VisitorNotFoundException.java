package com.susancode.fashionblog.exceptions.customexceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class VisitorNotFoundException extends RuntimeException{
    protected String message;
    protected HttpStatus status;

    public VisitorNotFoundException(String email) {
        this.message = String.format("BLog Visitor with email: %s not found", email);
        this.status = HttpStatus.NOT_FOUND;
    }

    public VisitorNotFoundException(Long id) {
        this.message = String.format("BLog Visitor with id: %s not found", id);
        this.status = HttpStatus.NOT_FOUND;
    }

}
