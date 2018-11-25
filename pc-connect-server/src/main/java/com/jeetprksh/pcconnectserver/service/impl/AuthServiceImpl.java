package com.jeetprksh.pcconnectserver.service.impl;

import com.jeetprksh.pcconnectserver.entity.Token;
import com.jeetprksh.pcconnectserver.entity.VerifyCode;
import com.jeetprksh.pcconnectserver.service.AuthService;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class AuthServiceImpl implements AuthService {

    // A <token, userName> map
    private Map<String, String> tokenMap = new HashMap<>();

    private String code;

    @Override
    public Token validateCode(VerifyCode verifyCode) throws Exception {
        if (!this.code.equals(verifyCode.getCode())) {
            throw new Exception("Invalid Code");
        } else {
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
        String code = new Random().ints(0,9)
                                    .limit(6)
                                    .boxed()
                                    .map(i -> Integer.toString(i))
                                    .reduce("", String::concat);
        this.code = code;
        return this.code;
    }

}
