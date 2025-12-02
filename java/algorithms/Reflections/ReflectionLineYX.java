import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class ReflectionLineYX extends JPanel {
    double x, y, rx, ry;
    int originX, originY;

    public ReflectionLineYX(double x, double y) {
        this.x = x;
        this.y = y;

        rx = y;
        ry = x;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        originX = getWidth() / 2;
        originY = getHeight() / 2;

        // Draw axes
        g.setColor(Color.BLACK);
        g.drawLine(0, originY, getWidth(), originY);  // X-axis
        g.drawLine(originX, 0, originX, getHeight()); // Y-axis

        // Draw line y = x
        g.setColor(Color.GRAY);
        g.drawLine(0, getHeight(), getWidth(), 0);

        // Convert to screen coordinates
        int sx = originX + (int)x;
        int sy = originY - (int)y;

        int srx = originX + (int)rx;
        int sry = originY - (int)ry;

        // Original point
        g.setColor(Color.RED);
        g.fillOval(sx - 5, sy - 5, 10, 10);

        // Reflected point
        g.setColor(Color.BLUE);
        g.fillOval(srx - 5, sry - 5, 10, 10);

        g.setColor(Color.BLACK);
        g.drawString("Original (" + x + ", " + y + ")", sx + 10, sy);
        g.drawString("Reflected (" + rx + ", " + ry + ")", srx + 10, sry);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter x: ");
        double x = sc.nextDouble();

        System.out.print("Enter y: ");
        double y = sc.nextDouble();

        JFrame f = new JFrame("Reflection about line y = x");
        f.add(new ReflectionLineYX(x, y));
        f.setSize(800, 600);
        f.setVisible(true);
    }
}
