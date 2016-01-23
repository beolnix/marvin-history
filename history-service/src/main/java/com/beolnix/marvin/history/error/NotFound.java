package com.beolnix.marvin.history.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by beolnix on 23/01/16.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFound extends RuntimeException {
}
