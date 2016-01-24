package com.beolnix.marvin.history.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BadRequest extends RuntimeException {
    public BadRequest(String message) {
        super(message);
    }
}
