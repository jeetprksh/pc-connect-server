package com.jeetprksh.pcconnect.server.websocket;

public class SocketUser {

  private final String userName;
  private final String token;

  public SocketUser(String userName, String token) {
    this.userName = userName;
    this.token = token;
  }

  public String getUserName() {
    return userName;
  }

  public String getToken() {
    return token;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    SocketUser that = (SocketUser) o;

    if (!userName.equals(that.userName)) return false;
    return token.equals(that.token);
  }

  @Override
  public int hashCode() {
    int result = userName.hashCode();
    result = 31 * result + token.hashCode();
    return result;
  }
}
