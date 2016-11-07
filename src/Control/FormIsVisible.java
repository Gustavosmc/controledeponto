package Control;

import static View.Main.desktopPane;

import javax.swing.JInternalFrame;


public class FormIsVisible {
    public static boolean isVisible(String f) {
        boolean t = false;
        
        JInternalFrame[] j = desktopPane.getAllFrames();
        for (JInternalFrame k : j) {
            if (k.getClass().getName().equals(f)) {
                t = true;
                break;
            }
        }
        return t;
    }
    public static JInternalFrame getFrame(String f) {
        
        JInternalFrame[] j = desktopPane.getAllFrames();
        JInternalFrame ret = null;
        for (JInternalFrame k : j) {
            if (k.getClass().getName().equals(f)) {
            	ret = k;
                break;
            }
        }
        return ret;
        
    }
  
}
