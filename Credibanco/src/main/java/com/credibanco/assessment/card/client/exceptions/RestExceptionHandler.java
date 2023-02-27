package com.credibanco.assessment.card.client.exceptions;

import java.security.ProviderException;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.credibanco.assessment.card.exceptions.ProviderExceptionAdd;

import org.springframework.core.Ordered;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler
{
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        String error = "Malformed JSON request";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "", error, ex));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        String error = "Validation Failed: ";
        for (ObjectError fieldError : ex.getBindingResult().getAllErrors())
        {
            error += fieldError.getDefaultMessage() + " - ";
        }
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "", error));
    }

    @ExceptionHandler(ProviderException.class)
    protected ResponseEntity<Object> handleProviderError(ProviderException ex)
    {
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,"400", ex.getMessage(), ex));
    }
    
    @ExceptionHandler(ProviderExceptionAdd.class)
    protected ResponseEntity<Object> handleProviderErrorAdd(ProviderException ex)
    {
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,"400", ex.getMessage(), ex));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleInternalError(Exception ex)
    {
        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "", ex.getMessage(), ex));

    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError)
    {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
    
}