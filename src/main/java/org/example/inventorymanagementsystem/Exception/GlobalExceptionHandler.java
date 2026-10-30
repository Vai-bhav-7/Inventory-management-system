package org.example.inventorymanagementsystem.Exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.Map;
import java.util.HashMap;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage())
                );

        return new ErrorResponse(
                400,
                "Validation failed",
                errors
        );
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorResponse handleProductNotFound(ProductNotFoundException ex) {

        return new ErrorResponse(
                404,
                ex.getMessage(),
                null
        );
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ErrorResponse handleInsufficientStock(InsufficientStockException ex) {
        return new ErrorResponse(400, ex.getMessage(), null);
    }
}
