package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel label;
    JSpinner rowSpinner;
    JSpinner colSpinner;
    JButton createBtn;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {
        //create the label and the spinner
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

    public int getRows() {
        return (int) rowSpinner.getValue();
    }

    public int getCols() {
        return (int) colSpinner.getValue();
    }
}

