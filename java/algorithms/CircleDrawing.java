import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CircleDrawing extends JPanel {
    // Define the center and radius
    int x0, y0, r;
    
    // Constructor to initialize center and radius
    public CircleDrawing(int x0, int y0, int r) {
        this.x0 = x0;
        this.y0 = y0;
        this.r = r;
    }

    // Method to draw the circle using the Midpoint Circle Drawing Algorithm
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Set the color to red
        g.setColor(Color.RED);
        
        // Initial parameters
        int x = r;
        int y = 0;
        int p = 5 / 4 - r;
        
        // The eight points are drawn using symmetry
        drawCirclePoints(g, x, y);
        
        // Loop to calculate the next points
        while (x > y) {
            y++;
            
            if (p < 0) {
                // If pk < 0, the next point is (xk + 1, yk)
                p = p + 2 * y + 1;
            } else {
                // If pk >= 0, the next point is (xk + 1, yk - 1)
                x--;
                p = p + 2 * (y - x) + 1;
            }
            
            // Draw the symmetrical points
            drawCirclePoints(g, x, y);
        }
    }

    // Helper method to draw points on all octants
    private void drawCirclePoints(Graphics g, int x, int y) {
        // Draw points on all eight octants of the circle
        g.fillRect(x0 + x, y0 + y, 1, 1); // (x, y)
        g.fillRect(x0 - x, y0 + y, 1, 1); // (-x, y)
        g.fillRect(x0 + x, y0 - y, 1, 1); // (x, -y)
        g.fillRect(x0 - x, y0 - y, 1, 1); // (-x, -y)
        g.fillRect(x0 + y, y0 + x, 1, 1); // (y, x)
        g.fillRect(x0 - y, y0 + x, 1, 1); // (-y, x)
        g.fillRect(x0 + y, y0 - x, 1, 1); // (y, -x)
        g.fillRect(x0 - y, y0 - x, 1, 1); // (-y, -x)
    }

    // Main method to set up the JFrame and draw the circle
    public static void main(String[] args) {
        // Set up the frame and window size
        JFrame frame = new JFrame("Circle Drawing using Midpoint Algorithm");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Center and radius of the circle
        int centerX = 200;
        int centerY = 200;
        int radius = 100;

        // Create the panel and add it to the frame
        CircleDrawing panel = new CircleDrawing(centerX, centerY, radius);
        frame.add(panel);
        
        // Make the frame visible
        frame.setVisible(true);
    }
}
