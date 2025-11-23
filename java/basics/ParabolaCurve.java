import javax.swing.*;
import java.awt.*;

public class ParabolaCurve extends JPanel {

    int x0, y0, maxX, maxY;

    // Constructor to initialize the center and axis limits
    public ParabolaCurve(int x0, int y0, int maxX, int maxY) {
        this.x0 = x0; // center x (the middle of the panel)
        this.y0 = y0; // center y (the middle of the panel)
        this.maxX = maxX; // maximum x value (the range for plotting)
        this.maxY = maxY; // maximum y value (for visualizing the curve)
    }

    // Method to draw the parabola curve y = 100 - x^2
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK); // Color of the curve

        // Loop through values of x in the range from -maxX to maxX
        for (int x = -maxX; x <= maxX; x++) {
            // Calculate y = 100 - x^2
            int y = 100 - (x * x);

            // Adjust coordinates to center the graph on (x0, y0)
            int pixelX = x0 + x;
            int pixelY = y0 - y; // Inverting y to match graphics coordinate system

            // Plot the point at (pixelX, pixelY)
            g.fillRect(pixelX, pixelY, 3, 3);  // 1x1 pixel as the point
        }
    }

    // Main method to create the JFrame and display the parabola
    public static void main(String[] args) {
        // Parameters: x0, y0 (center), maxX and maxY (range)
        int x0 = 400, y0 = 300, maxX = 15, maxY = 100; // Adjust as needed

        // Create the frame and set its properties
        JFrame frame = new JFrame("Parabola Curve: y = 100 - x^2");
        frame.setSize(800, 600); // Size of the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the panel for drawing and add it to the frame
        ParabolaCurve panel = new ParabolaCurve(x0, y0, maxX, maxY);
        frame.add(panel);

        // Display the frame
        frame.setVisible(true);
    }
}
