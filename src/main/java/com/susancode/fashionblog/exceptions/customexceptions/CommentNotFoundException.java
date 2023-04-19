package com.susancode.fashionblog.exceptions.customexceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CommentNotFoundException extends RuntimeException {
    protected String message;
    protected HttpStatus status;

    public CommentNotFoundException(Long id) {
        this.message = String.format("Comment with id: %s not found", id);
        this.status = HttpStatus.NOT_FOUND;
    }

}

