package com.lotto.lotto.controller;

import com.lotto.lotto.exception.MyAccountNotFoundException;
import com.lotto.lotto.exception.ResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AccountControllerExceptionHandler {

    @ExceptionHandler(MyAccountNotFoundException.class)
    public ResponseEntity<ResponseException> notFound(MyAccountNotFoundException execption) {
        ResponseException responseException = new ResponseException(execption.getMessage());
        return new ResponseEntity<ResponseException>(responseException, HttpStatus.NOT_FOUND);
    }
}
