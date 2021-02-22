/* Practical 1 Question 6 */

import java.awt.*;
import javax.swing.*;

public class FrameBorderLayout extends JFrame {

    public FrameBorderLayout() {

        JPanel btnPanel1 = new JPanel(new GridLayout(1, 2));
        JPanel btnPanel2 = new JPanel(new GridLayout(1, 2));

        for (int i = 1; i <= 2; i++) {
            btnPanel1.add(new JButton("panelOne" + i));
            btnPanel2.add(new JButton("panelTwo" + i));
        }

        JPanel controlPanel = new JPanel(new BorderLayout());
        controlPanel.add(btnPanel1, BorderLayout.CENTER);
        controlPanel.add(btnPanel2, BorderLayout.SOUTH);

        /* add control panel component to frame */
        add(controlPanel);

        setTitle("Border Layout Frame (P1Q6)");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 250);
        setVisible(true);
    }

    public static void main(String[] args) {
        FrameBorderLayout frame = new FrameBorderLayout();
    }
}
