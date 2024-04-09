package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

/**
 * ControlPanel is a JPanel that contains buttons for saving, loading, exporting and exiting the game.
 * It also contains a reference to the DrawingPanel.
 * The save button saves the current state of the game to a file.
 * The load button loads the state of the game from a file.
 */
public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton exitBtn = new JButton("Exit");
    JButton loadBtn = new JButton("Load");
    JButton saveBtn = new JButton("Save");
    JButton exportBtn = new JButton("Export to PNG");
    DrawingPanel drawingPanel;

    /**
     * Constructor for the ControlPanel class.
     * @param frame The MainFrame object that contains the ControlPanel.
     * @param drawingPanel The DrawingPanel object that contains the game.
     */
    public ControlPanel(MainFrame frame, DrawingPanel drawingPanel) {
        this.frame = frame;
        this.drawingPanel = drawingPanel;
        init();
    }

    /**
     * The init() method creates the buttons and adds them to the panel.
     * The buttons have ActionListeners that call the corresponding methods.
     */
    private void init() {

        exitBtn.setFocusable(false);
        add(exitBtn);
        loadBtn.addActionListener(e->this.loadGame());

        loadBtn.setFocusable(false);
        add(loadBtn);
        saveBtn.addActionListener(e->this.saveGame());

        saveBtn.setFocusable(false);
        add(saveBtn);
        exitBtn.addActionListener(this::exitGame);

        exportBtn.setFocusable(false);
        add(exportBtn);
        exportBtn.addActionListener(e -> this.drawingPanel.exportBoardToPNG());


    }

    /**
     * The exitGame() method disposes of the frame, effectively closing the application.
     * @param e The ActionEvent object that triggered the method.
     */
    private void exitGame(ActionEvent e) {
        frame.dispose();
    }

    /**
     * The saveGame() method saves the current state of the game to a file.
     * It uses an ObjectOutputStream to write the DrawingPanel object to a file.
     */
    private void saveGame() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("game_state.ser"))) {
            out.writeObject(drawingPanel);
            JOptionPane.showMessageDialog(this, "Game saved successfully.", "Save Game", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving game.", "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * The loadGame() method loads the state of the game from a file.
     * It uses an ObjectInputStream to read a DrawingPanel object from a file.
     */
    private void loadGame() {
        File fileToLoad = new File("game_state.ser");
        if (fileToLoad.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileToLoad))) {
                DrawingPanel savedPanel = (DrawingPanel) in.readObject();

                frame.remove(frame.canvas);
                frame.canvas = savedPanel;
                frame.add(frame.canvas, BorderLayout.CENTER);
                frame.canvas.revalidate();
                frame.canvas.repaint();
                frame.canvas.reattachListeners();

                JOptionPane.showMessageDialog(this, "Game loaded successfully.", "Load Game", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error loading game.", "Load Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No saved game to load.", "Load Error", JOptionPane.WARNING_MESSAGE);
        }
    }
}

