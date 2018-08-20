package com.epam.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserpicFileNotFoundException extends RuntimeException {

    public UserpicFileNotFoundException(String message) {
        super(message);
    }

    public UserpicFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
