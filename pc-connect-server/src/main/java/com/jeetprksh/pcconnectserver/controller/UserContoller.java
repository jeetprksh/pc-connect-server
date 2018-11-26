package com.jeetprksh.pcconnectserver.controller;

import com.jeetprksh.pcconnectserver.entity.Token;
import com.jeetprksh.pcconnectserver.entity.VerifyCode;
import com.jeetprksh.pcconnectserver.entity.http.Response;
import com.jeetprksh.pcconnectserver.service.AuthService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author Jeet Prakash
 * */
@CrossOrigin
@RestController()
public class UserContoller {

    @Autowired
    private AuthService authService;

    @GetMapping("/user/code/verify")
    public ResponseEntity<? extends Response> verifyCode(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "encoded") String encoded) throws Exception {
        byte[] decoded = Base64.decodeBase64(encoded);
        Token token = authService.validateCode(new VerifyCode(name, new String(decoded)));
        return ResponseEntity.ok().body(new Response(true, "Code Validated", token));
    }
}
