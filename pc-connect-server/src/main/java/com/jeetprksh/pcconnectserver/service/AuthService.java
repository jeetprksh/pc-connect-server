package com.jeetprksh.pcconnectserver.service;

import com.jeetprksh.pcconnectserver.entity.Token;
import com.jeetprksh.pcconnectserver.entity.VerifyCode;

/*
 * @author Jeet Prakash
 * */
public interface AuthService {

    Token validateCode(VerifyCode verifyCode) throws Exception;

    String verifyToken(String token) throws Exception;

    String generateCode();

}
