package classes;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ButtonPanel extends JPanel {
    private JButton addTask;
    private JButton clear;

    Border emptyBorder = BorderFactory.createEmptyBorder();

    // Constructor
    ButtonPanel() {
        this.setPreferredSize(new Dimension(400, 50));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));  // BoxLayout for horizontal arrangement

        addTask = new JButton("Add Task");
        addTask.setFont(new Font("Sans-serif", Font.BOLD, 20));
        addTask.setBackground(Color.GRAY);
        addTask.setForeground(Color.WHITE);

        clear = new JButton("Clear");
        clear.setFont(new Font("Sans-serif", Font.BOLD, 20));
        clear.setForeground(Color.WHITE);
        clear.setBackground(Color.GRAY);

        // Add horizontal glue that will adjust based on the window's width
        this.add(Box.createHorizontalGlue());  // Adds flexible space before
        this.add(addTask);
        this.add(Box.createHorizontalGlue());  // Adds flexible space between the buttons
        this.add(clear);
        this.add(Box.createHorizontalGlue());  // Adds flexible space after

        // Add a component listener to dynamically adjust the spacing when the window is resized
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateSpacing();
            }
        });
    }

    public JButton getaddTask()
    {

        return addTask;
    }

    public JButton getClear() {
        return clear;
    }

    // Method to update spacing between buttons based on the current width
    private void updateSpacing() {
        // Calculate spacing based on the panel's width
        int panelWidth = this.getWidth();
        int buttonWidth = addTask.getPreferredSize().width + clear.getPreferredSize().width;
        int remainingSpace = panelWidth - buttonWidth;
        int spaceBetweenButtons = remainingSpace / 3;  // Divide the remaining space into three parts

        // Update the layout with dynamic spacing
        this.removeAll();  // Clear all components
        this.add(Box.createHorizontalStrut(spaceBetweenButtons));  // Space before first button
        this.add(addTask);
        this.add(Box.createHorizontalStrut(spaceBetweenButtons));  // Space between buttons
        this.add(clear);
        this.add(Box.createHorizontalStrut(spaceBetweenButtons));  // Space after second button

        this.revalidate();  // Refresh the layout
        this.repaint();     // Redraw the panel
    }
}

