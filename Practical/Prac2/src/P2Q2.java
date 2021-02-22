/* Practical 2 Question 2  */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class P2Q2 extends JFrame {

    private JButton jbtnRed = new JButton("Red");
    private JButton jbtnGreen = new JButton("Green");
    private JButton jbtnBlue = new JButton("Blue");
    private JLabel jlblColor = new JLabel("Color");

    public P2Q2() {
        JPanel buttonPanels = new JPanel();
        buttonPanels.add(jbtnRed);
        buttonPanels.add(jbtnGreen);
        buttonPanels.add(jbtnBlue);

        jlblColor.setHorizontalAlignment(JLabel.CENTER);
        jlblColor.setFont(new Font("Cambria", Font.BOLD, 20));

        add(jlblColor, BorderLayout.NORTH);
        add(buttonPanels, BorderLayout.CENTER);

        jbtnRed.addActionListener(new ChangeColorListenerClass());
        jbtnGreen.addActionListener(new ChangeColorListenerClass());
        jbtnBlue.addActionListener(new ChangeColorListenerClass());
    }

    private class ChangeColorListenerClass implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jbtnRed) {     //@ getActionCommand() return name => jbtnRed return "Red"
                jlblColor.setForeground(Color.RED);
            } else if (e.getSource() == jbtnGreen) {
                jlblColor.setForeground(Color.GREEN);
            } else if (e.getSource() == jbtnBlue) {
                jlblColor.setForeground(Color.BLUE);
            }
        }
    }

    public static void main(String[] args) {
        P2Q2 frame = new P2Q2();
        frame.setTitle("Detect Source");
        frame.setSize(400, 120);
        frame.setLocation(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
