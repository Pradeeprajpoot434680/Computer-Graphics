// import javax.swing.*;
// import java.awt.*;

// public class RotationSquare extends JPanel {

//     // Square (4 points, closed)
//     int[][] shape = {
//         {300, 300, 1},
//         {400, 300, 1},
//         {400, 400, 1},
//         {300, 400, 1},
//         {300, 300, 1}
//     };

//     // Draw shape
//     void drawShape(Graphics g, int[][] pts) {
//         for (int i = 0; i < pts.length - 1; i++) {
//             g.drawLine(pts[i][0], pts[i][1], pts[i + 1][0], pts[i + 1][1]);
//         }
//     }

//     // Correct Matrix multiplication
//     int[][] multiply(int[][] points, int[][] T) {
//         int[][] result = new int[points.length][3];

//         for (int i = 0; i < points.length; i++) {
//             int x = points[i][0];
//             int y = points[i][1];
//             int w = points[i][2];

//             result[i][0] = x * T[0][0] + y * T[0][1] + w * T[0][2];  // x'
//             result[i][1] = x * T[1][0] + y * T[1][1] + w * T[1][2];  // y'
//             result[i][2] = x * T[2][0] + y * T[2][1] + w * T[2][2];  // 1'
//         }

//         return result;
//     }

//     // Rotation transformation (about origin)
//     int[][] rotate(int[][] shape, double angle) {
//         double rad = Math.toRadians(angle);

//         int[][] R = {
//             {(int)Math.round(Math.cos(rad)), (int)Math.round(-Math.sin(rad)), 0},
//             {(int)Math.round(Math.sin(rad)), (int)Math.round(Math.cos(rad)),  0},
//             {0, 0, 1}
//         };

//         return multiply(shape, R);
//     }

//     @Override
//     public void paintComponent(Graphics g) {
//         super.paintComponent(g);

//         g.setColor(Color.RED);      // Original
//         drawShape(g, shape);

//         // Rotate by 45 degrees
//         int[][] rotated = rotate(shape, 45);

//         g.setColor(Color.BLUE);     // Rotated
//         drawShape(g, rotated);
//     }

//     public static void main(String[] args) {
//         JFrame frame = new JFrame("Rotation Transformation");
//         RotationSquare rs = new RotationSquare();
//         frame.add(rs);
//         frame.setSize(800, 800);
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setVisible(true);
//     }
// }



import javax.swing.*;
import java.awt.*;

public class RotationSquare extends JPanel {

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

    // ---------- ROTATE AROUND PIVOT (cx, cy) ----------
    int[][] rotateAroundPoint(int[][] shape, double angle, int cx, int cy) {

        double rad = Math.toRadians(angle);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);

        int[][] result = new int[shape.length][3];

        for (int i = 0; i < shape.length; i++) {

            int x = shape[i][0];
            int y = shape[i][1];

            // Translate to origin
            int xt = x - cx;
            int yt = y - cy;

            // Rotate
            int xr = (int)(xt * cos - yt * sin);
            int yr = (int)(xt * sin + yt * cos);

            // Translate back
            result[i][0] = xr + cx;
            result[i][1] = yr + cy;
            result[i][2] = 1;
        }

        return result;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw original
        g.setColor(Color.RED);
        drawShape(g, shape);

        // center of square
        int cx = 350;
        int cy = 350;

        int[][] rotated = rotateAroundPoint(shape, 45, cx, cy);

        g.setColor(Color.BLUE);
        drawShape(g, rotated);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Rotation About Center");
        frame.add(new RotationSquare());
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
