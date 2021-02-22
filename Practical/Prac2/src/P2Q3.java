/* Practical 2 Question 3  */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class P2Q3 extends JFrame {

    private JButton jbtnRed = new JButton("Red");
    private JButton jbtnGreen = new JButton("Green");
    private JButton jbtnBlue = new JButton("Blue");
    private JLabel jlblColor = new JLabel("Color");

    public P2Q3() {
        JPanel buttonPanels = new JPanel();
        buttonPanels.add(jbtnRed);
        buttonPanels.add(jbtnGreen);
        buttonPanels.add(jbtnBlue);

        jlblColor.setHorizontalAlignment(JLabel.CENTER);
        jlblColor.setFont(new Font("Cambria", Font.BOLD, 20));

        add(jlblColor, BorderLayout.NORTH);
        add(buttonPanels, BorderLayout.CENTER);

        /*  use anoymonous inner class if the component's action
            is only use one time/ not sharing with other component */
        jbtnRed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jlblColor.setForeground(Color.RED);
            }
        });

        jbtnGreen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jlblColor.setForeground(Color.GREEN);
            }
        });

        jbtnBlue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jlblColor.setForeground(Color.BLUE);
            }
        });
    }

    public static void main(String[] args) {
        P2Q3 frame = new P2Q3();
        frame.setTitle("Primary Colors");
        frame.setSize(400, 120);
        frame.setLocation(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
