import javax.swing.*;
import java.awt.*;

public class DrawShapes extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw a point (as a small filled rectangle)
        g.setColor(Color.BLACK);
        //putpixel

        g.fillRect(50, 50, 2, 2);

        // Draw a line
        g.setColor(Color.BLUE);
        g.drawLine(100, 100, 100, 500);
        g.setColor(Color.BLACK);

        // Draw a circle (oval with equal width & height)
        g.drawOval(250, 50, 200, 100);

        // draw arc
        g.drawArc(250, 50, 300, 400, 0, 90);//parameters => (x, y, width, height, startAngle, arcAngle)
        // Draw a rectangle
        g.drawRect(400, 50, 120, 150); // starting point ,length and width

        // Filled circle
        g.fillOval(250, 200, 100, 100);

        // Filled rectangle
        g.fillRect(400, 200, 120, 80);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Drawing Shapes in Java");
        DrawShapes panel = new DrawShapes();
        frame.add(panel);
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
