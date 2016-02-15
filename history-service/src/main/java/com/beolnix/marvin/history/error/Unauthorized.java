package com.beolnix.marvin.history.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by beolnix on 15/02/16.
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class Unauthorized extends RuntimeException {
}
