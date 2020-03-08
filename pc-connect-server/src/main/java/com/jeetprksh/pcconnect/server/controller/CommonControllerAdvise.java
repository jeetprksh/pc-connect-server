package com.jeetprksh.pcconnect.server.controller;

import com.jeetprksh.pcconnect.server.entity.http.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
 * @author Jeet Prakash
 * */
@ControllerAdvice
public class CommonControllerAdvise {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<? extends Response> exception(Exception ex) {
        if (ex.getMessage().equalsIgnoreCase("Invalid Token"))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new Response(false, ex.getMessage(), null));
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new Response(false, ex.getMessage(), null));
    }

}
