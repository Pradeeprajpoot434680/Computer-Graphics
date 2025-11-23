import javax.swing.*;
import java.awt.*;

class Bresenham extends JPanel {

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Starting and ending points
        int x0 = 50, y0 = 50, x1 = 400, y1 = 100;

        // Compute deltas
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        // Determine direction of step
        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;

        // Initialize error term
        int err = dx - dy;

        int x = x0;
        int y = y0;

        while (true) {
            g.fillRect(x, y, 1, 1); // Draw pixel

            if (x == x1 && y == y1)
                break;

            int e2 = 2 * err;

            if (e2 > -dy) {
                err -= dy;
                x += sx;
            }

            if (e2 < dx) {
                err += dx;
                y += sy;
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Bresenham panel = new Bresenham();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.add(panel);
        frame.setVisible(true);
    }
}
