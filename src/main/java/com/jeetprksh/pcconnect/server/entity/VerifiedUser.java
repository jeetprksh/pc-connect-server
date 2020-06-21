package com.jeetprksh.pcconnect.server.entity;

/*
 * @author Jeet Prakash
 * */
public class VerifiedUser {

  private final String userId;
  private final String userName;
  private final String token;

  public VerifiedUser(String userId, String userName, String token) {
    this.userId = userId;
    this.userName = userName;
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  public String getUserId() {
    return userId;
  }

  public String getUserName() {
    return userName;
  }

}
