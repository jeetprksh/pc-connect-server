package com.jeetprksh.pcconnect.server.controller;

import com.jeetprksh.pcconnect.server.entity.Token;
import com.jeetprksh.pcconnect.server.entity.VerifyCode;
import com.jeetprksh.pcconnect.server.entity.http.Response;
import com.jeetprksh.pcconnect.server.service.AuthService;

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
public class UserController {

  @Autowired
  private AuthService authService;

  @GetMapping("/user/code/verify")
  public ResponseEntity<? extends Response> verifyCode(
      @RequestParam(value = "name") String name,
      @RequestParam(value = "encoded") String encoded) throws Exception {
    Token token = authService.validateCode(new VerifyCode(name, new String(Base64.decodeBase64(encoded))));
    return ResponseEntity.ok().body(new Response(true, "Code Validated", token));
  }

}
