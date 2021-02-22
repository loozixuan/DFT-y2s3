
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class EnterPasswordFrame extends JFrame {

    private JPasswordField jpfPassword = new JPasswordField(15);
    private JTextArea jtaMessage = new JTextArea(5, 35);
    private JButton jbtnSubmit = new JButton("Submit");

    public EnterPasswordFrame() {
        JPanel jpl = new JPanel(new GridLayout(2, 2));
        jpl.add(new JLabel("Enter your password"));
        jpl.add(jpfPassword);
        jpl.add(new JLabel());
        jpl.add(jbtnSubmit);

        add(jpl, BorderLayout.CENTER);
        add(jtaMessage, BorderLayout.SOUTH);
        jtaMessage.setEditable(false);
        jtaMessage.setLineWrap(true); //if previous line exceeds size will wrap to next line
        jtaMessage.setWrapStyleWord(true);

        setTitle("Enter Password Frame");
        pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        jbtnSubmit.addActionListener(new ButtonListener());
        jpfPassword.addActionListener(new ButtonListener()); //without clicking submit button also can trigger listener
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String inputPassword = new String(jpfPassword.getPassword());
            try {
                Password pw = new Password(inputPassword);
                jtaMessage.setForeground(Color.BLUE);
                jtaMessage.setText("Congratulations, your password is valid");
            } catch (InvalidPasswordException ex) {
                jtaMessage.setForeground(Color.red);
                jtaMessage.setText(ex.getMessage());
                jpfPassword.grabFocus();
            }
        }
    }

    public static void main(String[] args) {
        new EnterPasswordFrame();
    }
}
