package com.jeetprksh.pcconnect.ui.root;

import com.jeetprksh.pcconnect.ui.root.server.ServerControlPanel;

import javax.swing.*;

/*
 * @author Jeet Prakash
 * */
public class RootFrame extends JFrame {

    private ServerControlPanel serverControlPanel;

    public RootFrame() {
        serverControlPanel = new ServerControlPanel();
        createFrame();
    }

    private void createFrame() {
        this.setSize(500, 300);
        this.setTitle("PC Connect");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(serverControlPanel);
    }

}
