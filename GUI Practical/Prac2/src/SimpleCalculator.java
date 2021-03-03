
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SimpleCalculator extends JFrame {

    private JTextField jtfNum1 = new JTextField();
    private JTextField jtfNum2 = new JTextField();
    private JTextField jtfResult = new JTextField();

    private JButton jbtnAdd = new JButton("Add");
    private JButton jbtnSubtract = new JButton("Subtract");
    private JButton jbtnMutiply = new JButton("Mutiply");
    private JButton jbtnDivide = new JButton("Divide");

    public SimpleCalculator() {

        JPanel jpNorth = new JPanel();
        jpNorth.setLayout(new GridLayout(3, 2));
        jpNorth.add(new JLabel("First Number"));
        jpNorth.add(jtfNum1);
        jpNorth.add(new JLabel("Second Number"));
        jpNorth.add(jtfNum2);
        jpNorth.add(new JLabel("Result"));
        jpNorth.add(jtfResult);
        jtfResult.setEditable(false);

        JPanel jpBtnGrp = new JPanel();
        jpBtnGrp.add(jbtnAdd);
        jpBtnGrp.add(jbtnSubtract);
        jpBtnGrp.add(jbtnMutiply);
        jpBtnGrp.add(jbtnDivide);

        add(jpNorth, BorderLayout.NORTH);
        add(jpBtnGrp, BorderLayout.CENTER);

        jbtnAdd.addActionListener(new AddListener());
        jbtnSubtract.addActionListener(new SubtractListener());
        jbtnMutiply.addActionListener(new MultiplyListener());
        jbtnDivide.addActionListener(new DivideListener());

        setTitle("Simple Calculator");
        setSize(450, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    class AddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int result = Integer.parseInt(jtfNum1.getText()) + Integer.parseInt(jtfNum2.getText());
                jtfResult.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                jtfResult.setText(String.valueOf("Number required"));
            }
        }

    }

    class SubtractListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int result = Integer.parseInt(jtfNum1.getText()) - Integer.parseInt(jtfNum2.getText());
                jtfResult.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                jtfResult.setText(String.valueOf("Number required"));
            }
        }

    }

    class MultiplyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int result = Integer.parseInt(jtfNum1.getText()) * Integer.parseInt(jtfNum2.getText());
                jtfResult.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                jtfResult.setText(String.valueOf("Number required"));
            }
        }
    }

    class DivideListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int result = Integer.parseInt(jtfNum1.getText()) / Integer.parseInt(jtfNum2.getText());
                jtfResult.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                jtfResult.setText(String.valueOf("Number required"));
            } catch (ArithmeticException ex) {
                jtfResult.setText(String.valueOf("Cannot divide by 0"));
            }
        }
    }

    public static void main(String[] args) {
        new SimpleCalculator();
    }
}
