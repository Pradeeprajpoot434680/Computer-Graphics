import javax.swing.*;
import java.awt.*;

public class MidpointEllipse extends JPanel {
    int x0, y0, a, b;

    // Constructor to initialize center and axes
    public MidpointEllipse(int x0, int y0, int a, int b) {
        this.x0 = x0;
        this.y0 = y0;
        this.a = a;
        this.b = b;
    }

    // Method to draw the ellipse using the Midpoint Ellipse Algorithm
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);

        // First region
        int x = 0;
        int y = b;
        int p1 = (b * b) - (a * a * b) + (a * a / 4);
        drawEllipsePoints(g, x, y);

        // First region decision making
        while (a * a * (y - 0.5) > b * b * (x + 1)) {
            if (p1 < 0) {
                p1 = p1 + 2 * b * b * x + 3 * b * b;
            } else {
                p1 = p1 + 2 * b * b * x - 2 * a * a * y + 2 * a * a + 3 * b * b;
                y--;
            }
            x++;
            drawEllipsePoints(g, x, y);
        }

        // Second region
        int p2 = (int) (b * b * (x + 0.5) * (x + 0.5) + a * a * (y - 1) * (y - 1) - a * a * b * b);
        while (y > 0) {
            if (p2 <= 0) {
                p2 = p2 + 2 * b * b * x + 3 * b * b;
            } else {
                p2 = p2 - 2 * a * a * y + 3 * a * a;
                x++;
            }
            y--;
            drawEllipsePoints(g, x, y);
        }
    }

    // Helper method to draw points using symmetry in all 4 quadrants
    private void drawEllipsePoints(Graphics g, int x, int y) {
        // Draw points in all four quadrants using symmetry
        g.fillRect(x0 + x, y0 + y, 1, 1);  // First quadrant (x, y)
        g.fillRect(x0 - x, y0 + y, 1, 1);  // Second quadrant (-x, y)
        g.fillRect(x0 + x, y0 - y, 1, 1);  // Third quadrant (x, -y)
        g.fillRect(x0 - x, y0 - y, 1, 1);  // Fourth quadrant (-x, -y)
        // g.fillRect(x0 + y, y0 + x, 1, 1);  // First quadrant (y, x)
        // g.fillRect(x0 - y, y0 + x, 1, 1);  // Second quadrant (-y, x)
        // g.fillRect(x0 + y, y0 - x, 1, 1);  // Third quadrant (y, -x)
        // g.fillRect(x0 - y, y0 - x, 1, 1);  // Fourth quadrant (-y, -x)
    }

    // Main method to set up the JFrame and draw the ellipse
    public static void main(String[] args) {
        JFrame frame = new JFrame("Midpoint Ellipse Algorithm");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center of ellipse (x0, y0) and axes lengths a and b
        int x0 = 200, y0 = 200, a = 150, b = 100;

        // Create the panel and add it to the frame
        MidpointEllipse panel = new MidpointEllipse(x0, y0, a, b);
        frame.add(panel);

        // Make the frame visible
        frame.setVisible(true);
    }
}

