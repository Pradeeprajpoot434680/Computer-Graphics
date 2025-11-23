import java.awt.*;
import javax.swing.*;
import java.util.Scanner;

public class ShearSquareDraw extends JPanel {

    private double[][] originalPoints;
    private double shx, shy;

    public ShearSquareDraw(double[][] points, double shx, double shy) {
        this.originalPoints = points;
        this.shx = shx;
        this.shy = shy;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));

        // Draw original square in blue
        g2.setColor(Color.BLUE);
        drawPolygon(g2, originalPoints);

        // Apply shear and draw sheared square in red
        g2.setColor(Color.RED);
        double[][] shearedPoints = new double[originalPoints.length][2];
        for (int i = 0; i < originalPoints.length; i++) {
            double x = originalPoints[i][0];
            double y = originalPoints[i][1];
            shearedPoints[i][0] = x + shx * y;
            shearedPoints[i][1] = y + shy * x;
        }
        drawPolygon(g2, shearedPoints);
    }

    private void drawPolygon(Graphics2D g2, double[][] points) {
        int n = points.length;
        for (int i = 0; i < n; i++) {
            int x1 = (int) (points[i][0] * 50) + 50;      // scale & translate for visibility
            int y1 = (int) (points[i][1] * 50) + 50;
            int x2 = (int) (points[(i + 1) % n][0] * 50) + 50;
            int y2 = (int) (points[(i + 1) % n][1] * 50) + 50;
            g2.drawLine(x1, y1, x2, y2);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Square coordinates (A, B, C, D)
        double[][] points = {
            {0, 0},   // A
            {2, 0},   // B
            {2, 2},   // C
            {0, 2}    // D
        };

        System.out.print("Enter Shx (Shear in X-direction): ");
        double shx = sc.nextDouble();

        System.out.print("Enter Shy (Shear in Y-direction): ");
        double shy = sc.nextDouble();

        sc.close();

        JFrame frame = new JFrame("Shear Square");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.add(new ShearSquareDraw(points, shx, shy));
        frame.setVisible(true);
    }
}
