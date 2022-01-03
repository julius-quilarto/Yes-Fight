import java.awt.*;
import javax.swing.*;
import java.awt.geom.Line2D;

public class SupplementCanvas extends JComponent{
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2f));
        g2.setColor(Color.RED);
        g2.draw(new Line2D.Double(50, 150, 250, 350));
        g2.setColor(Color.GREEN);
        g2.draw(new Line2D.Double(250, 350, 350, 250));
        g2.setColor(Color.BLUE);
        g2.draw(new Line2D.Double(350, 250, 150, 50));
        g2.setColor(Color.YELLOW);
        g2.draw(new Line2D.Double(150, 50, 50, 150));
        g2.setColor(Color.BLACK);
        g2.draw(new Line2D.Double(0, 0, 400, 400));
    }
}
