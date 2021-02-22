/* Tutorial Question 10 */

import javax.swing.JFrame;
import javax.swing.ImageIcon;

public class TestImageIcon extends JFrame{
    
    ImageIcon icon;
    
    TestImageIcon(){
        setTitle("TestImageIcon");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 100);
        setVisible(true);
        
        /* [Method 1] -- new folder -- name as images -- place icon image here -- write out below code  */
        // icon = new ImageIcon("image/icon.png");
        // setIconImage(icon.getImage());

        /* [Method 2] -- follow methods in GUI Notes */
        icon = new ImageIcon(getClass().getResource("images/icon.png"));
        setIconImage(icon.getImage());
    }
    
    public static void main(String[] args) {
        new TestImageIcon();
    }
}



