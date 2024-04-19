package org.example.srg3springminiproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFoundException(NotFoundException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
        problemDetail.setTitle("Not Found");
        problemDetail.setProperty("DataTime", LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        for(var fieldError : ex.getBindingResult().getFieldErrors()){
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ProblemDetail problemDetail = ProblemDetail.forStatus(
                HttpStatus.BAD_REQUEST
        );
        problemDetail.setTitle("Bed Request");
        problemDetail.setProperty("Error", errors);
        return problemDetail;
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ProblemDetail handlerMethodValidationException(HandlerMethodValidationException ex){
        Map<String, String> errors = new HashMap<>();
        for(var errorParam : ex.getAllValidationResults()){
            String parameterName =  errorParam.getMethodParameter().getParameterName();
            for(var error : errorParam.getResolvableErrors()){
                errors.put(parameterName, error.getDefaultMessage());
            }
        }
        ProblemDetail problemDetail = ProblemDetail.forStatus(
                HttpStatus.BAD_REQUEST
        );
        problemDetail.setTitle("Bed Request");
        problemDetail.setProperty("Error", errors);
        return problemDetail;
    }

}

