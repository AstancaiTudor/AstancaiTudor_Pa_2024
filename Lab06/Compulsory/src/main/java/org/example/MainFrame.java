package org.example;

import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JButton;

import static javax.swing.SwingConstants.CENTER;


public class MainFrame extends JFrame {
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;

    public MainFrame() {
        super("My Game");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        configPanel = new ConfigPanel(this);
        add(configPanel, BorderLayout.NORTH);

        controlPanel = new ControlPanel(this);
        add(controlPanel, BorderLayout.SOUTH);

        canvas = new DrawingPanel(this);  //arrange the components in the container (frame) //JFrame uses a BorderLayout by default
        add(canvas, CENTER); //this is BorderLayout.CENTER

        pack();
    }
}

