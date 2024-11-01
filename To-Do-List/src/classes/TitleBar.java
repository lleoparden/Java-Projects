package classes;

import javax.swing.*;
import java.awt.*;

public class TitleBar extends JPanel
{
    //construtor
    TitleBar()
    {
        this.setPreferredSize(new Dimension(400,50));
        this.setBackground(Color.GRAY);
        JLabel titletext = new JLabel("To-Do list");
        titletext.setPreferredSize(new Dimension(200,40));
        titletext.setFont(new Font("Sans-serif",Font.BOLD,20 ));
        titletext.setHorizontalAlignment(JLabel.CENTER);
        titletext.setForeground(Color.WHITE);

        this.add(titletext);

    }
}
