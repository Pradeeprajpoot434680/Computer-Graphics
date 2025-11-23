import javax.swing.*;
import java.awt.*;

public class ScalingSquare extends JPanel {

    // Original square
    int[][] shape = {
        {300, 300, 1},
        {400, 300, 1},
        {400, 400, 1},
        {300, 400, 1},
        {300, 300, 1}
    };

    // Draw the square
    void drawShape(Graphics g, int[][] pts) {
        for (int i = 0; i < pts.length - 1; i++) {
            g.drawLine(pts[i][0], pts[i][1], pts[i + 1][0], pts[i + 1][1]);
        }
    }

    // ---------- SCALE AROUND PIVOT (cx, cy) ----------
    int[][] scaleAroundPoint(int[][] shape, double sx, double sy, int cx, int cy) {

        int[][] result = new int[shape.length][3];

        for (int i = 0; i < shape.length; i++) {

            int x = shape[i][0];
            int y = shape[i][1];

            // Translate to origin
            int xt = x - cx;
            int yt = y - cy;

            // Scale
            int xs = (int)(xt * sx);
            int ys = (int)(yt * sy);

            // Translate back
            result[i][0] = xs + cx;
            result[i][1] = ys + cy;
            result[i][2] = 1;
        }

        return result;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw original – RED
        g.setColor(Color.RED);
        drawShape(g, shape);

        // Center of square
        int cx = 350;
        int cy = 350;

        // Scale 1.5x in X, 1.5x in Y
        int[][] scaled = scaleAroundPoint(shape, 1.5, 1.5, cx, cy);

        // Draw scaled – BLUE
        g.setColor(Color.BLUE);
        drawShape(g, scaled);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Scaling Square");
        frame.add(new ScalingSquare());
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
