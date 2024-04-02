package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton exitBtn = new JButton("Exit");
    JButton loadBtn = new JButton("Load");
    JButton saveBtn = new JButton("Save");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {

        exitBtn.setFocusable(false);
        add(exitBtn);
        loadBtn.addActionListener(this::loadGame);

        loadBtn.setFocusable(false);
        add(loadBtn);
        saveBtn.addActionListener(this::saveGame);

        saveBtn.setFocusable(false);
        add(saveBtn);
        exitBtn.addActionListener(this::exitGame);
    }
    private void exitGame(ActionEvent e) {
        frame.dispose();
    }

    private void loadGame(ActionEvent e) {
        //TODO
    }
    private void saveGame(ActionEvent e) {
        //TODO
    }
}

