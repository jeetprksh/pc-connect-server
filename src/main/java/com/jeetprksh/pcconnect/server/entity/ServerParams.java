package com.jeetprksh.pcconnect.server.entity;

import java.util.List;

public class ServerParams {

  private String serverPort;
  private List<String> sharedDirectories;

  public ServerParams(String serverPort, List<String> sharedDirectories) {
    this.serverPort = serverPort;
    this.sharedDirectories = sharedDirectories;
  }

  public String getServerPort() {
    return serverPort;
  }

  public List<String> getSharedDirectories() {
    return sharedDirectories;
  }

}
