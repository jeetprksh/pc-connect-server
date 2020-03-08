package com.jeetprksh.pcconnect.ui.root;

import com.jeetprksh.pcconnect.ui.root.server.ServerControllerPanel;

import javax.swing.*;

/*
 * @author Jeet Prakash
 * */
public class RootFrame extends JFrame {

    private ServerControllerPanel serverControllerPanel;

    public RootFrame() {
        serverControllerPanel = new ServerControllerPanel();
        createFrame();
    }

    private void createFrame() {
        this.setSize(500, 300);
        this.setTitle("PC Connect");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(serverControllerPanel);
    }

}
