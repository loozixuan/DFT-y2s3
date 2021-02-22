/* Practical 2 Question 1  */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class P2Q1 extends JFrame {

    private JButton jbtnRed = new JButton("Red");
    private JButton jbtnGreen = new JButton("Green");
    private JButton jbtnBlue = new JButton("Blue");
    private JLabel jlblColor = new JLabel("Color");

    public P2Q1() {
        // alternative layout => setLayout(new GridLayout(2,1));
        JPanel buttonPanels = new JPanel();
        buttonPanels.add(jbtnRed);
        buttonPanels.add(jbtnGreen);
        buttonPanels.add(jbtnBlue);

        jlblColor.setHorizontalAlignment(JLabel.CENTER);
        jlblColor.setFont(new Font("Cambria", Font.BOLD, 20));

        add(jlblColor, BorderLayout.NORTH);
        add(buttonPanels, BorderLayout.CENTER);

        jbtnRed.addActionListener(new ChangeRedListenerClass());
        jbtnGreen.addActionListener(new ChangeGreenListenerClass());
        jbtnBlue.addActionListener(new ChangeBlueListenerClass());
    }

    public static void main(String[] args) {
        P2Q1 frame = new P2Q1();
        frame.setTitle("Primary Colors");
        frame.setSize(400, 120);
        frame.setLocation(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    class ChangeRedListenerClass implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            jlblColor.setForeground(Color.RED);
        }
    }

    class ChangeGreenListenerClass implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            jlblColor.setForeground(Color.GREEN);
        }
    }

    class ChangeBlueListenerClass implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            jlblColor.setForeground(Color.BLUE);
        }
    }
}
