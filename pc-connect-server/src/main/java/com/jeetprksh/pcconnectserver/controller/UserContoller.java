package com.jeetprksh.pcconnectserver.controller;

import com.jeetprksh.pcconnectserver.entity.Token;
import com.jeetprksh.pcconnectserver.entity.VerifyCode;
import com.jeetprksh.pcconnectserver.entity.http.Request;
import com.jeetprksh.pcconnectserver.entity.http.Response;
import com.jeetprksh.pcconnectserver.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserContoller {

    @Autowired
    private AuthService authService;

    @PostMapping("/code/verify")
    public ResponseEntity<? extends Response> verifyCode(RequestEntity <Request> request) throws Exception {
        VerifyCode code = (VerifyCode) request.getBody().getData();
        Token token = authService.validateCode(code);
        return ResponseEntity.ok().body(new Response(true, "Code Validated", token));
    }
}
