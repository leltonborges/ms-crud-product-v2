package org.ms.crud.exceptions;

import org.ms.crud.services.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CrudExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public final ResponseEntity<StandarError> handlerBadRequestException(ProductNotFoundException ex, HttpServletRequest request){
        StandarError err =
                new StandarError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST,
                        "Not Found", ex.getMessage(), request.getRequestURI());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(err);
    }
}
