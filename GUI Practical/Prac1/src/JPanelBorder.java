/* Practical 1 Question 7 Part JFrame */

import java.awt.*;
import javax.swing.*;

public class JPanelBorder extends JFrame {

    ButtonPanels btn1;
    ButtonPanels btn2;

    JPanelBorder() {
        setLayout(new BorderLayout());
        ButtonPanels jp = new ButtonPanels("1", "2");
        ButtonPanels jp2 = new ButtonPanels("3", "4");
        add(jp, BorderLayout.CENTER);
        add(jp2, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        JPanelBorder frame = new JPanelBorder();
        frame.setTitle("Border Layout Frame");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setVisible(true);
    }
}
