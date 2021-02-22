/* NumberFormatException P3Q1 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class P3Q1 extends JFrame {

    private JPasswordField jtfPwinput = new JPasswordField();
    private JButton jbtnSubmit = new JButton("Submit");
    private JTextArea jtaMessage = new JTextArea();
    private JLabel jlPassword = new JLabel("Enter Your Password");

    public P3Q1() {
        this.setTitle("Set Password");
        this.setSize(500, 250);
        this.setLocation(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        JPanel jpPanel = new JPanel(new GridLayout(2, 1));
        jpPanel.add(jtfPwinput);
        jpPanel.add(jbtnSubmit);

        JPanel jpSetPwPanel = new JPanel(new GridLayout(1, 2));
        jpSetPwPanel.add(jlPassword);
        jpSetPwPanel.add(jpPanel);

        this.add(jpSetPwPanel, BorderLayout.NORTH);
        this.add(jtaMessage, BorderLayout.CENTER);

        jbtnSubmit.addActionListener(new SubmitButtonListenerClass());
    }

    class SubmitButtonListenerClass implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jbtnSubmit) {
                String password = new String(jtfPwinput.getPassword());
                if (password.length() == 0) {
                    jtaMessage.setForeground(Color.RED);
                    jtaMessage.setText("Invalid password: Password cannot be empty or null");
                } else {
                    try {
                        int pw = Integer.parseInt(password);
                        jtaMessage.setForeground(Color.BLUE);
                        jtaMessage.setText("Congratulations! Your password is valid");
                    } catch (NumberFormatException ex) {
                        jtaMessage.setForeground(Color.RED);
                        jtaMessage.setText("Your password should only consists numbers");
                        jtfPwinput.setText("");
                        jtfPwinput.requestFocusInWindow();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new P3Q1();
    }
}
