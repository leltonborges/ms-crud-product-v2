package org.ms.crud.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;
import java.io.Serializable;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends  RuntimeException implements Serializable {
    @Serial
    private static final long serialVersionUID = -16300131161207875L;

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
