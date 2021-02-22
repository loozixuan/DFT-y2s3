/* Practical 1 Question 1 (b) */

import javax.swing.JOptionPane;

public class P1Q1b {

    public static void main(String[] args) {

        int option = JOptionPane.YES_OPTION;

        do {
            double fahrenheit = Double.parseDouble(JOptionPane.showInputDialog("Enter Temperature (Fahrenheit) : "));

            Double celcius = 5.0 / 9.0 * (fahrenheit - 32.0);
            JOptionPane.showMessageDialog(null,
                    "Temperature in Celsius : \n" + String.format("%.2f", celcius) + " °C"
            );

            option = JOptionPane.showConfirmDialog(null, "Would you like to enter another temperature(F)?");
        } while (option == JOptionPane.YES_OPTION);
    }
}
