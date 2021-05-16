
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;
import javax.swing.JFrame;

class Walk extends JComponent {
    double guyx;
    double guyy;
    double sunx;
    double suny;
    int degrees;
    
    public Walk() {
        guyx = 0;
        guyy = 0;
        sunx = -100000;
        suny = 0;
        degrees = 0;
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        //super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        Ellipse2D circle = new Ellipse2D.Double(guyx + 100, guyy + 100, 10, 10);
        g2d.setColor(Color.magenta);
        g2d.fill(circle);
        
        circle = new Ellipse2D.Double(100, 100, 10, 10);
        g2d.setColor(Color.magenta);
        g2d.fill(circle);
    }
    
    public void moveSun() {
        sunx = (Math.cos(0.01745328627927352441191151881988) * sunx) + (Math.sin(0.01745328627927352441191151881988) * suny);
        suny = (-Math.sin(0.01745328627927352441191151881988) * sunx) + (Math.cos(0.01745328627927352441191151881988) * suny);
    }
    
    public void moveGuy() {
        float deltaX = (float) (sunx - guyx);
        float deltaY = (float) (suny - guyy);
        float angle = (float) Math.atan2( deltaY, deltaX );
        guyx += 1 * Math.cos( angle );
        guyy += 1 * Math.sin( angle );
    }
}

    
    
public class Sunwalk {
    public static JFrame frame;
    
    public static void main(String[] args) throws InterruptedException {
        Walk sunwalk = new Walk();
        frame = new JFrame();
        frame.setSize(1000, 1000);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(sunwalk);
        frame.setVisible(true);
        
        for(int i = 0; i < 180; i++) {
            sunwalk.moveGuy();
            sunwalk.moveSun();
            System.out.println(sunwalk.guyx + " " + sunwalk.guyy);
            sunwalk.paintImmediately(0, 0, 970, 970);
            sunwalk.degrees++;
            Thread.sleep(20);
        }       
    }
}
