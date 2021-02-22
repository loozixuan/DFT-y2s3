/* Practical 1 Question 7 Part extends JPanel */

import java.awt.*;
import javax.swing.*;

class ButtonPanels extends JPanel {

    ButtonPanels(String btn1, String btn2) {
        setLayout(new GridLayout(1, 2));
        this.add(new JButton(btn1));
        this.add(new JButton(btn2));
    }
}
