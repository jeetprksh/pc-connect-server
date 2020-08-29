package com.jeetprksh.pcconnect.server.service;

import com.jeetprksh.pcconnect.server.entity.User;
import com.jeetprksh.pcconnect.server.entity.VerifiedUser;
import com.jeetprksh.pcconnect.server.entity.VerifyCode;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;

/*
 * @author Jeet Prakash
 * */
@Component
public class UserService {

  private final Logger logger = Logger.getLogger(UserService.class.getName());

  private final Map<String, User> tokenMap = new HashMap<>();

  private String serverAuthCode;

  public VerifiedUser validateCode(VerifyCode verifyCode) throws Exception {
    String userName = verifyCode.getName();
    if (!this.serverAuthCode.equals(verifyCode.getCode())) {
      logger.info(userName + " entered wrong code.");
      throw new Exception("Invalid Code");
    } else {
      logger.info(userName + " got verified.");
      String token = UUID.randomUUID().toString();
      String userId = generateUserId();
      this.tokenMap.put(token, new User(userId, userName));
      return new VerifiedUser(userId, userName, token);
    }
  }

  public User verifyToken(String token) throws Exception {
    User userName = this.tokenMap.get(token);
    if (!Objects.isNull(userName)) {
      return userName;
    } else {
      throw new Exception("Invalid Token");
    }
  }

  public String generateCode() {
    this.serverAuthCode = new Random().ints(0, 9)
        .limit(6)
        .boxed()
        .map(i -> Integer.toString(i))
        .reduce("", String::concat);
    return this.serverAuthCode;
  }

  private String generateUserId() {
    Random random = new Random();
    return random.ints(48, 122 + 1)
            .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
            .limit(7)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
  }

}
