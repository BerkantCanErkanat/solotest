
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import javax.swing.JFrame;


public class SoloTest extends JFrame{

    public SoloTest(String title) throws HeadlessException {
        super(title);
    }

   
    public static void main(String[] args) {
        
        
        SoloTest st = new SoloTest("Solo Test");
        Oyun oyun = new Oyun();
        st.setFocusable(false);
        oyun.requestFocus();
        oyun.addMouseListener(oyun);
        st.add(oyun);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//tum screenı almak ıcın.
        st.setSize(screenSize.width, screenSize.height);
        st.setResizable(false);
        st.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        st.setVisible(true);
        
    }
    
}
