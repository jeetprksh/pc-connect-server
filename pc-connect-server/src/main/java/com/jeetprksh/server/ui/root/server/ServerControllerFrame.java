package com.jeetprksh.server.ui.root.server;

import com.jeetprksh.pcconnectserver.PcConnectServer;

import javax.swing.*;

public class ServerControllerFrame extends JPanel {

    private JButton startServer;
    private JButton restartServer;

    public  ServerControllerFrame() {
        createButtons();
    }

    private void createButtons() {
        startServer = new JButton("Start");
        startServer.addActionListener((event) -> {
            PcConnectServer.start(new String[]{});
        });

        restartServer = new JButton("Restart");
        restartServer.addActionListener((event) -> {
            PcConnectServer.restart();
        });
        this.add(startServer);
        this.add(restartServer);
    }

}
