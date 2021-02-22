/* Practical 1 Question 3 & 4 */

import java.awt.*;
import javax.swing.*;

public class StudentInfoFrame extends JFrame {

    public StudentInfoFrame() {

        setTitle("Student Information");
//        setLayout(new FlowLayout(FlowLayout.LEFT));
        setLayout(new GridLayout(4, 2));        // Grid Layout
        setSize(300, 300);

        add(new JLabel("Reg No :"));
        add(new JTextField(10));

        add(new JLabel("Name :"));
        add(new JTextField(20));

        add(new JLabel("Programme Code :"));
        add(new JTextField(10));

        add(new JButton("Submit"));

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        StudentInfoFrame studentInfoFrame = new StudentInfoFrame();
    }
}
