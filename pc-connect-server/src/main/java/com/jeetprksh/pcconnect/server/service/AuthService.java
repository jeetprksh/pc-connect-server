package com.jeetprksh.pcconnect.server.service;

import com.jeetprksh.pcconnect.server.entity.Token;
import com.jeetprksh.pcconnect.server.entity.VerifyCode;

/*
 * @author Jeet Prakash
 * */
public interface AuthService {

    Token validateCode(VerifyCode verifyCode) throws Exception;

    String verifyToken(String token) throws Exception;

    String generateCode();

}
