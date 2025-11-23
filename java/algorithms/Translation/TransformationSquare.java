import javax.swing.*;
import java.awt.*;

public class TransformationSquare extends JPanel {

    // Square shape: A simple square (4 points)
    int[][] shape = {
        {100, 100, 1},  // Point 1
        {200, 100, 1},  // Point 2
        {200, 200, 1},  // Point 3
        {100, 200, 1},  // Point 4
        {100, 100, 1}   // Close the shape (point 1 again)
    };

    // Method to draw the shape by connecting points
    void drawShape(Graphics g, int[][] pts) {
        for (int i = 0; i < pts.length - 1; i++) {
            g.drawLine(pts[i][0], pts[i][1], pts[i + 1][0], pts[i + 1][1]);
        }
    }

    // Matrix multiplication: Multiply points by transformation matrix T
    int[][] multiply(int[][] points, int[][] T) {
        int[][] result = new int[points.length][3];

        for (int i = 0; i < points.length; i++) {
            int x = points[i][0];
            int y = points[i][1];
            int w = points[i][2];

            result[i][0] = x * T[0][0] + y * T[0][1] + w * T[0][2];  // x'
            result[i][1] = x * T[1][0] + y * T[1][1] + w * T[1][2];  // y'
            result[i][2] = x * T[2][0] + y * T[2][1] + w * T[2][2];  // 1'
        }

        return result;
    }

    // Translation transformation
    int[][] translate(int[][] shape, int tx, int ty) {
        int[][] T = {
            {1, 0, tx},  // Translation matrix
            {0, 1, ty},
            {0, 0, 1}
        };

        return multiply(shape, T);  // Apply transformation
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.RED);  // Color for original shape
        drawShape(g, shape);  // Draw the original square

        // Apply translation (translate by 150 pixels along both x and y axis)
        int[][] translated = translate(shape, 150, 150);  // Translate by (150, 150)

        g.setColor(Color.BLUE);  // Color for translated shape
        drawShape(g, translated);  // Draw the translated square
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Transformation Square");
        TransformationSquare t = new TransformationSquare();
        frame.add(t);
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
