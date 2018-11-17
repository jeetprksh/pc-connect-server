package com.jeetprksh.pcconnectserver.controller;

import com.jeetprksh.pcconnectserver.entity.Test;
import com.jeetprksh.pcconnectserver.entity.http.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController("test")
public class TestController {

    @GetMapping()
    public ResponseEntity<? extends Response> test() {
        Response res = new Response(true, "Test Ping.", new Test("I am OK!"));
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(res);
    }

}
