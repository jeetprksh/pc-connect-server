package com.jeetprksh.server.ui.root;

import com.jeetprksh.server.ui.root.server.ServerControllerFrame;

import javax.swing.*;

public class RootFrame extends JFrame {

    private ServerControllerFrame serverControllerFrame;

    public RootFrame() {
        serverControllerFrame = new ServerControllerFrame();
        createFrame();
    }

    private void createFrame() {
        this.setSize(500, 300);
        this.setTitle("PC Connect");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(serverControllerFrame);
    }

}
