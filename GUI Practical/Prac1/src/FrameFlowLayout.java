/* Practical 1 Question 5 */

//can do in grid layout using 1 JPanel
import javax.swing.*;

public class FrameFlowLayout extends JFrame {

    public FrameFlowLayout() {

        JPanel btnPanel1 = new JPanel();
        JPanel btnPanel2 = new JPanel();

        for (int i = 1; i <= 2; i++) {
            btnPanel1.add(new JButton("" + i));
            btnPanel2.add(new JButton("" + i));
        }

        JPanel controlPanel = new JPanel();
        controlPanel.add(btnPanel1);
        controlPanel.add(btnPanel2);

        /* add control panel component to frame */
        add(controlPanel);

        setTitle("Flow Layout Frame");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setVisible(true);
    }

    public static void main(String[] args) {
        FrameFlowLayout frame = new FrameFlowLayout();
    }
}
