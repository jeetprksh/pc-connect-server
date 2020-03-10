package com.jeetprksh.pcconnect.ui.root.server;

import com.jeetprksh.pcconnect.server.PcConnectServer;
import com.jeetprksh.pcconnect.server.entity.ServerParams;

import javax.swing.*;

/*
 * @author Jeet Prakash
 * */
public class ServerControlPanel extends JPanel {

  private JButton startServer;
  private JButton restartServer;
  private JButton stopServer;
  private JTextField serverPort;

  private SharedDirectoriesPanel sharedDirectoriesPanel;

  public ServerControlPanel(SharedDirectoriesPanel sharedDirectoriesPanel) {
    this.sharedDirectoriesPanel = sharedDirectoriesPanel;
    createButtons();
  }

  private void createButtons() {
    serverPort = new JTextField(6);

    startServer = new JButton("Start");
    startServer.addActionListener((event) -> {
      ServerParams params = new ServerParams(serverPort.getText(), sharedDirectoriesPanel.getSharedDirectories());
      if (isNumber(params.getServerPort())) {
        try {
          PcConnectServer.start(params);
        } catch (Exception ex) {
          showError(ex.getMessage());
        }
      } else {
        showError("Server Port is not a valid number.");
      }
    });

    restartServer = new JButton("Restart");
    restartServer.addActionListener((event) -> {
      ServerParams params = new ServerParams(serverPort.getText(), sharedDirectoriesPanel.getSharedDirectories());
      if (isNumber(params.getServerPort())) {
        try {
          PcConnectServer.restart(params);
        } catch (Exception ex) {
          showError(ex.getMessage());
        }
      } else {
        showError("Server Port is not a valid number.");
      }
    });

    stopServer = new JButton("Stop");
    stopServer.addActionListener((event) -> {
      PcConnectServer.stop();
    });

    this.add(serverPort);
    this.add(startServer);
    this.add(stopServer);
    this.add(restartServer);
  }

  private boolean isNumber(String port) {
    try {
      Integer.parseInt(port);
    } catch (Exception ex) {
      return false;
    }
    return true;
  }

  private void showError(String message) {
    JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
  }

}
