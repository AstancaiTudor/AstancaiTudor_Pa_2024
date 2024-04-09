package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ConfigPanel is a JPanel that contains the configuration components of the application.
 * It contains a label, two spinners and a button.
 * The spinners are used to set the number of rows and columns of the grid.
 * @author Tudor
 */
public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel label;
    JSpinner rowSpinner;
    JSpinner colSpinner;
    JButton createBtn;

    /**
     * Constructor for the ConfigPanel class.
     * @param frame The MainFrame object that contains the ConfigPanel.
     */
    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    /**
     * The init() method creates the label, the spinners and the button and adds them to the panel.
     * The button has an ActionListener that initializes the DrawingPanel with the new number of rows and columns.
     */
    private void init() {
        label = new JLabel("Grid size:");

        rowSpinner = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        colSpinner = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));

        createBtn = new JButton("Create");
        createBtn.setFocusable(false);
        createBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.canvas.init(frame.configPanel.getRows(), frame.configPanel.getCols());
            }
        });

        add(label);
        add(rowSpinner);
        add(colSpinner);
        add(createBtn, BorderLayout.EAST);

    }

    /**
     * The getRows() method returns the value of the rowSpinner.
     * @return The value of the rowSpinner.
     */
    public int getRows() {
        return (int) rowSpinner.getValue();
    }

    /**
     * The getCols() method returns the value of the colSpinner.
     * @return The value of the colSpinner.
     */
    public int getCols() {
        return (int) colSpinner.getValue();
    }
}

