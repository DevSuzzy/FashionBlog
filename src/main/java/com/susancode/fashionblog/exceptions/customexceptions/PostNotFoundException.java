package com.susancode.fashionblog.exceptions.customexceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class PostNotFoundException extends RuntimeException {
    protected String message;
    protected HttpStatus status;

    public PostNotFoundException(Long id) {
        this.message = String.format("Post with id: %s not found", id);
        this.status = HttpStatus.NOT_FOUND;
    }

}
