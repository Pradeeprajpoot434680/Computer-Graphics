import javax.swing.*;
import java.awt.*;

public class PointRotation extends JPanel {

    // Single point (x, y, 1)
    int[] point = {200, 100, 1};

    // ROTATION MATRIX: multiply point × R
    int[] rotatePoint(int[] p, double angle) {
        double rad = Math.toRadians(angle);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);

        int[][] R = {
            {(int)Math.round(cos), (int)Math.round(-sin), 0},
            {(int)Math.round(sin), (int)Math.round(cos),  0},
            {0, 0, 1}
        };

        int[] result = new int[3];
        result[0] = p[0] * R[0][0] + p[1] * R[0][1] + p[2] * R[0][2]; // x'
        result[1] = p[0] * R[1][0] + p[1] * R[1][1] + p[2] * R[1][2]; // y'
        result[2] = 1;

        return result;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw original point
        g.setColor(Color.RED);
        g.fillOval(point[0], point[1], 10, 10);

        // Rotate point by 45 degrees
        int[] rotated = rotatePoint(point, 45);

        // Draw rotated point
        g.setColor(Color.BLUE);
        g.fillOval(rotated[0], rotated[1], 10, 10);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Point Rotation");
        PointRotation pr = new PointRotation();
        frame.add(pr);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
