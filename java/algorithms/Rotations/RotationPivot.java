import javax.swing.*;
import java.awt.*;

public class RotationPivot extends JPanel {

    // Square: 4 points + closing point
    int[][] shape = {
        {300, 300, 1},
        {400, 300, 1},
        {400, 400, 1},
        {300, 400, 1},
        {300, 300, 1}
    };

    // Draw shape
    void drawShape(Graphics g, int[][] pts) {
        for (int i = 0; i < pts.length - 1; i++) {
            g.drawLine(pts[i][0], pts[i][1], pts[i + 1][0], pts[i + 1][1]);
        }
    }

    // ---------- ROTATE ABOUT ANY PIVOT POINT (px, py) ----------
    int[][] rotateAboutPivot(int[][] shape, double angle, int px, int py) {

        double rad = Math.toRadians(angle);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);

        int[][] result = new int[shape.length][3];

        for (int i = 0; i < shape.length; i++) {

            int x = shape[i][0];
            int y = shape[i][1];

            // 1. Translate point so pivot becomes origin
            int xt = x - px;
            int yt = y - py;

            // 2. Rotate around origin
            int xr = (int)(xt * cos - yt * sin);
            int yr = (int)(xt * sin + yt * cos);

            // 3. Translate back
            result[i][0] = xr + px;
            result[i][1] = yr + py;
            result[i][2] = 1;
        }

        return result;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.RED);  
        drawShape(g, shape);    // original

        // Set pivot point (you can choose anything)
        int px = 300;  // Pivot X
        int py = 300;  // Pivot Y

        // Rotate around pivot by 45 degrees
        int[][] rotated = rotateAboutPivot(shape, 45, px, py);

        g.setColor(Color.BLUE);
        drawShape(g, rotated);

        // Draw pivot point
        g.setColor(Color.BLACK);
        g.fillOval(px - 5, py - 5, 10, 10);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Rotation About Pivot Point");
        frame.add(new RotationPivot());
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
