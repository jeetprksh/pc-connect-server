package com.jeetprksh.pcconnectserver.service.impl;

import com.jeetprksh.pcconnectserver.entity.Token;
import com.jeetprksh.pcconnectserver.entity.VerifyCode;
import com.jeetprksh.pcconnectserver.service.AuthService;
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
public class AuthServiceImpl implements AuthService {

    private final Logger logger = Logger.getLogger(AuthServiceImpl.class.getName());

    // A <token, userName> map
    private Map<String, String> tokenMap = new HashMap<>();

    private String code;

    @Override
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

    @Override
    public String verifyToken(String token) throws Exception {
        String userName = this.tokenMap.get(token);
        if (userName != null) {
            return userName;
        } else {
            throw new Exception("Invalid Token");
        }
    }

    @Override
    public String generateCode() {
        this.code = new Random().ints(0, 9)
                                .limit(6)
                                .boxed()
                                .map(i -> Integer.toString(i))
                                .reduce("", String::concat);
        return this.code;
    }

}
