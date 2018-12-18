package com.jeetprksh.server.ui.root.server;

import com.jeetprksh.pcconnectserver.PcConnectServer;

import javax.swing.*;

/*
 * @author Jeet Prakash
 * */
public class ServerControllerPanel extends JPanel {

    private JButton startServer;
    private JButton restartServer;

    public ServerControllerPanel() {
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
