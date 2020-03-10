package com.jeetprksh.pcconnect.ui.root.server;

import com.jeetprksh.pcconnect.server.entity.ServerParams;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestartServer implements ActionListener {

  private ServerParams serverParams;

  public RestartServer(ServerParams serverParams) {
    this.serverParams = serverParams;
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {

  }
}
