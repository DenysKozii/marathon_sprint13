package com.softserve.sprint13.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.servlet.http.HttpServletResponse;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IncorrectIdException extends RuntimeException {
    public IncorrectIdException(String message) {
        super(message);

    }
}
