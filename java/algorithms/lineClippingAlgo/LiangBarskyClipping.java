package lineClippingAlgo;
import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class LiangBarskyClipping extends JPanel {

    double x1, y1, x2, y2;   // Original line endpoints
    double xmin = -100, ymin = -100, xmax = 100, ymax = 100; // Clipping window
    boolean clipped = false;
    double cx1, cy1, cx2, cy2; // Clipped coordinates

    public LiangBarskyClipping(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

        liangBarsky();
    }

    // Liang-Barsky Algorithm
    void liangBarsky() {
        double dx = x2 - x1;
        double dy = y2 - y1;

        double p[] = {-dx, dx, -dy, dy};
        double q[] = {x1 - xmin, xmax - x1, y1 - ymin, ymax - y1};

        double u1 = 0.0, u2 = 1.0;

        for (int i = 0; i < 4; i++) {
            if (p[i] == 0 && q[i] < 0) return; // Line parallel and outside

            double r = q[i] / p[i];

            if (p[i] < 0) u1 = Math.max(u1, r);
            else if (p[i] > 0) u2 = Math.min(u2, r);
        }

        if (u1 > u2) return; // Line is outside

        // Compute clipped endpoints
        cx1 = x1 + u1 * (x2 - x1);
        cy1 = y1 + u1 * (y2 - y1);

        cx2 = x1 + u2 * (x2 - x1);
        cy2 = y1 + u2 * (y2 - y1);

        clipped = true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int originX = getWidth() / 2;
        int originY = getHeight() / 2;

        // Draw axes
        g.setColor(Color.GRAY);
        g.drawLine(0, originY, getWidth(), originY);
        g.drawLine(originX, 0, originX, getHeight());

        // Draw clipping window rectangle
        g.setColor(Color.BLUE);
        drawRect(g, xmin, ymin, xmax, ymax, originX, originY);

        // Draw original line (RED)
        g.setColor(Color.RED);
        drawLine(g, x1, y1, x2, y2, originX, originY);

        // Draw clipped line (GREEN)
        if (clipped) {
            g.setColor(Color.GREEN);
            drawLine(g, cx1, cy1, cx2, cy2, originX, originY);
        }
    }

    // Convert and draw a line
    void drawLine(Graphics g, double x1, double y1, double x2, double y2,
                  int originX, int originY) {
        g.drawLine(originX + (int)x1, originY - (int)y1,
                   originX + (int)x2, originY - (int)y2);
    }

    // Convert and draw the clipping window rectangle
    void drawRect(Graphics g, double xmin, double ymin, double xmax, double ymax,
                  int originX, int originY) {
        int x = originX + (int)xmin;
        int y = originY - (int)ymax;
        int w = (int)(xmax - xmin);
        int h = (int)(ymax - ymin);

        g.drawRect(x, y, w, h);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter x1: ");
        double x1 = sc.nextDouble();
        System.out.print("Enter y1: ");
        double y1 = sc.nextDouble();

        System.out.print("Enter x2: ");
        double x2 = sc.nextDouble();
        System.out.print("Enter y2: ");
        double y2 = sc.nextDouble();

        JFrame frame = new JFrame("Liang-Barsky Line Clipping");
        LiangBarskyClipping panel = new LiangBarskyClipping(x1, y1, x2, y2);

        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
