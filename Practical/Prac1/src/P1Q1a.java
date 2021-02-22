/* Practical 1 Question 1 (a) */

import javax.swing.JOptionPane;

public class P1Q1a {

    public static void main(String[] args) {
        double fahrenheit = Double.parseDouble(JOptionPane.showInputDialog("Enter Temperature (Fahrenheit) : "));

        Double celcius = 5.0 / 9.0 * (fahrenheit - 32.0);
        JOptionPane.showMessageDialog(null,
                "Temperature in Celsius : \n" + String.format("%.2f", celcius) + " °C"
        );
    }
}

//public class P1Q1a {
//
//    public static void main(String[] args){
//        String tempInput = JOptionPane.showInputDialog
//        ("Enter Temperature (Fahrenheit) : ");
//
//        double tempFahrenheit = Double.parseDouble(tempInput);
//        double tempCelsius = 5.0/9.0 * (tempFahrenheit - 32.0);
//
//        JOptionPane.showMessageDialog(null,
//                "Temperature in Celsius : \n" + String.format("%.2f", tempCelsius) + " °C"
//        );
//    }
//}

