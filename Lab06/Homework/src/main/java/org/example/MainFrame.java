package org.example;

import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JButton;

import static javax.swing.SwingConstants.CENTER;

/**
 * The MainFrame class is the main class of the application. It extends JFrame and contains the ConfigPanel, ControlPanel and DrawingPanel.
 * @author Tudor
 */
public class MainFrame extends JFrame {
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;

    /**
     * Constructor for the MainFrame class.
     * It sets the title of the frame and calls the init() method.
     */
    public MainFrame() {
        super("My Game");
        init();
    }

    /**
     * The init() method sets the default close operation, creates the ConfigPanel, DrawingPanel and ControlPanel and adds them to the frame.
     */
    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        configPanel = new ConfigPanel(this);
        add(configPanel, BorderLayout.NORTH);

        canvas = new DrawingPanel(this);
        add(canvas, CENTER);

        controlPanel = new ControlPanel(this, canvas);
        add(controlPanel, BorderLayout.SOUTH);

        pack();
    }

}

