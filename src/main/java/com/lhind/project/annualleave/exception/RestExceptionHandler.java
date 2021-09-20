package com.lhind.project.annualleave.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> genericException(Exception ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        response.setErrorMessage(ex.getLocalizedMessage());
        response.setTimestamp(LocalDateTime.now());
        logger.error("An error ocurred! ", response);
        return new ResponseEntity<ExceptionResponse>(response, response.getStatus());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleEntityNotFound(EntityNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setStatus(HttpStatus.NOT_FOUND);
        response.setErrorMessage(ex.getLocalizedMessage());
        response.setTimestamp(LocalDateTime.now());
        logger.error("An error ocurred! ", response);
        return new ResponseEntity<ExceptionResponse>(response, response.getStatus());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFound(IllegalArgumentException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setErrorMessage(ex.getLocalizedMessage());
        response.setTimestamp(LocalDateTime.now());

        logger.error("An error ocurred! ", response);
        return new ResponseEntity<ExceptionResponse>(response, response.getStatus());
    }

    @ExceptionHandler(ClassNotFoundException.class)
    public ResponseEntity<ExceptionResponse> classNotFound(ClassNotFoundException ex) {

        ExceptionResponse response = new ExceptionResponse();
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setErrorMessage(ex.getLocalizedMessage());
        response.setTimestamp(LocalDateTime.now());

        logger.error("An error ocurred! ", response);
        return new ResponseEntity<ExceptionResponse>(response, response.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";

        return buildResponseEntity(new ExceptionResponse(HttpStatus.BAD_REQUEST, error));

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Not valid method argument";
        List<String> errors = new ArrayList<String>();
        for (FieldError error_ : ex.getBindingResult().getFieldErrors()) {
            errors.add(error_.getField() + ": " + error_.getDefaultMessage());
        }
        for (ObjectError error_ : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error_.getObjectName() + ":" + error_.getDefaultMessage());
        }
        return buildResponseEntity(new ExceptionResponse(HttpStatus.BAD_REQUEST, error, errors));

    }


    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Path variable is missing";

        return buildResponseEntity(new ExceptionResponse(HttpStatus.BAD_REQUEST, error));
    }


    private ResponseEntity<Object> buildResponseEntity(ExceptionResponse apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}
