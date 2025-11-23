import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class ShearXY extends JPanel {

    double x, y, sx, sy;
    double shx, shy;

    public ShearXY(double x, double y, double shx, double shy) {
        this.x = x;
        this.y = y;
        this.shx = shx;
        this.shy = shy;

        // Apply shear in both directions
        sx = x + shx * y;
        sy = y + shy * x;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int ox = getWidth() / 2;
        int oy = getHeight() / 2;

        // Draw axes
        g.drawLine(0, oy, getWidth(), oy);
        g.drawLine(ox, 0, ox, getHeight());

        // Original point
        int px = ox + (int)x;
        int py = oy - (int)y;

        g.setColor(Color.RED);
        g.fillOval(px - 5, py - 5, 10, 10);

        // Sheared point
        int psx = ox + (int)sx;
        int psy = oy - (int)sy;

        g.setColor(Color.BLUE);
        g.fillOval(psx - 5, psy - 5, 10, 10);

        g.setColor(Color.BLACK);
        g.drawString("Original (" + x + "," + y + ")", px + 10, py);
        g.drawString("Sheared (" + sx + "," + sy + ")", psx + 10, psy);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter x: ");
        double x = sc.nextDouble();

        System.out.print("Enter y: ");
        double y = sc.nextDouble();

        System.out.print("Enter shear Shx: ");
        double shx = sc.nextDouble();

        System.out.print("Enter shear Shy: ");
        double shy = sc.nextDouble();

        JFrame f = new JFrame("Shearing in X & Y direction");
        f.add(new ShearXY(x, y, shx, shy));
        f.setSize(800, 600);
        f.setVisible(true);
    }
}
