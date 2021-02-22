/* ArrayIndexOutOfBoundsException P3Q2 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class P3Q2 extends JFrame {

    private JTextField jtfArrInput = new JTextField();
    private JTextField jtfArrElement = new JTextField();
    private JButton jbtnShowArr = new JButton("Show Element");

    public P3Q2() {
        this.setTitle("Display: Show Bounds Error");
        this.setSize(400, 120);
        this.setLocation(300, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        JPanel jpArray = new JPanel();
        jpArray.setLayout(new GridLayout(2, 2));
        jpArray.add(new JLabel("Array Index"));
        jpArray.add(jtfArrInput);
        jpArray.add(new JLabel("Array Element"));
        jpArray.add(jtfArrElement);
        jtfArrElement.setEditable(false);

        JPanel jpButton = new JPanel();
        jpButton.add(jbtnShowArr);
        this.add(jpArray, BorderLayout.NORTH);
        this.add(jpButton, BorderLayout.CENTER);

        jbtnShowArr.addActionListener(new HandleArrayListenerClass());
    }

    /*  Returns an array with 10000 randomly chosen integers  */
    public static int[] getArray() {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 10000) + 1;
        }
        return array;
    }

    class HandleArrayListenerClass implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int[] array = getArray();

            try {
                int arrayIndex = Integer.parseInt(jtfArrInput.getText());
                jtfArrElement.setText("" + array[arrayIndex]);
            } catch (ArrayIndexOutOfBoundsException ex) {
                jtfArrElement.setText("Out of bounds");
            } catch (NumberFormatException ex) {
                jtfArrElement.setText("Non a numeirc value");
            }
        }
    }

    public static void main(String[] args) {
        new P3Q2();
    }
}
