package com.jeetprksh.pcconnect.server.service;

import com.jeetprksh.pcconnect.server.entity.Token;
import com.jeetprksh.pcconnect.server.entity.VerifyCode;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;

/*
 * @author Jeet Prakash
 * */
@Component
public class AuthService {

  private final Logger logger = Logger.getLogger(AuthService.class.getName());

  // A <token, userName> map
  private final Map<String, String> tokenMap = new HashMap<>();

  private String code;

  public Token validateCode(VerifyCode verifyCode) throws Exception {
    if (!this.code.equals(verifyCode.getCode())) {
      logger.info(verifyCode.getName() + " entered wrong code.");
      throw new Exception("Invalid Code");
    } else {
      logger.info(verifyCode.getName() + " got verified.");
      String token = UUID.randomUUID().toString();
      this.tokenMap.put(token, verifyCode.getName());
      return new Token(token);
    }
  }

  public String verifyToken(String token) throws Exception {
    String userName = this.tokenMap.get(token);
    if (userName != null) {
      return userName;
    } else {
      throw new Exception("Invalid Token");
    }
  }

  public String generateCode() {
    this.code = new Random().ints(0, 9)
        .limit(6)
        .boxed()
        .map(i -> Integer.toString(i))
        .reduce("", String::concat);
    return this.code;
  }

}
