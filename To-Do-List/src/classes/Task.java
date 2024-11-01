package classes;

import javax.swing.*;
import java.awt.*;

public class Task extends JPanel
{
    private JLabel index;
    private JTextField taskName;
    private JButton done;

    public boolean checked;  // Keep this public to access in List class

    // Constructor
    Task()
    {
        this.setPreferredSize(new Dimension(40, 20));
        this.setBackground(Color.BLUE);
        this.setLayout(new BorderLayout());

        checked = false;  // Task starts as not done

        index = new JLabel("");
        index.setPreferredSize(new Dimension(20, 20));
        index.setHorizontalTextPosition(10);
        this.add(index, BorderLayout.WEST);

        taskName = new JTextField("Your Task Here");
        taskName.setBorder(BorderFactory.createEmptyBorder());
        taskName.setBackground(Color.BLUE);
        this.add(taskName, BorderLayout.CENTER);

        done = new JButton("done");
        done.setPreferredSize(new Dimension(40, 20));
        done.setBorder(BorderFactory.createEmptyBorder());
        this.add(done, BorderLayout.EAST);
    }

    public JButton getDone() {
        return done;
    }

    public void changeIndex(int i)
    {
        this.index.setText(i + "");
        this.revalidate();
    }

    public void changeState()
    {
        this.setBackground(Color.CYAN);  // Mark as done by changing color
        taskName.setBackground(Color.CYAN);
        checked = true;  // Task is now marked as done
    }

    public boolean getState()
    {
        return checked;
    }
}
