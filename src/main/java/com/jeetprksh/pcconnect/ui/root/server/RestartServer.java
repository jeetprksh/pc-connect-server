package com.jeetprksh.pcconnect.ui.root.server;

import com.jeetprksh.pcconnect.server.PcConnectServer;
import com.jeetprksh.pcconnect.server.entity.ServerParams;

import java.awt.event.ActionEvent;

/*
 * @author Jeet Prakash
 * */
public class RestartServer implements ServerActionListener {

  private final ServerControlPanel panel;

  RestartServer(ServerControlPanel panel) {
    this.panel = panel;
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    ServerParams params = new ServerParams(
            panel.getServerPort().getText(), panel.getSharedDirectoriesPanel().getSharedDirectories());
    if (isNumber(params.getServerPort())) {
      try {
        PcConnectServer.restart(params);
      } catch (Exception ex) {
        showError(panel, ex.getMessage());
      }
    } else {
      showError(panel, "Server Port is not a valid number.");
    }
  }

}
