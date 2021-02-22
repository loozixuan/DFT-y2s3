/* Practical 1 Question 2 */
import javax.swing.JButton;
import javax.swing.JFrame;

public class OneFrame extends JFrame {

//    private JButton jbtnHello = new JButton("Hello");
    public OneFrame() {
        super("One Frame");
        add(new JButton("Hello"));
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        OneFrame myFrame = new OneFrame(); // or directly create new OneFrame();
    }
}
