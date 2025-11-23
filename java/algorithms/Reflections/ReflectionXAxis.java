import java.util.Scanner;
import javax.swing.*;
import java.awt.*;

public class ReflectionXAxis extends JPanel {
    private double x, y, reflectedX, reflectedY;

    // Origin in center
    int originX, originY;

    public ReflectionXAxis(double x, double y) {
        this.x = x;
        this.y = y;

        // Reflect the point about X-axis (mathematically)
        reflectedX = x;
        reflectedY = -y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Center of screen = origin (0,0)
        originX = getWidth() / 2;
        originY = getHeight() / 2;

        // Draw axes
        g.setColor(Color.BLACK);
        g.drawLine(0, originY, getWidth(), originY);  // X-axis
        g.drawLine(originX, 0, originX, getHeight()); // Y-axis

        // Convert math -> screen coordinates
        int screenX = originX + (int) x;
        int screenY = originY - (int) y;   // NOTICE THE '-'

        int screenRX = originX + (int) reflectedX;
        int screenRY = originY - (int) reflectedY; // NOTICE THE '-'

        // Draw original point (RED)
        g.setColor(Color.RED);
        g.fillOval(screenX - 5, screenY - 5, 10, 10);

        // Draw reflected point (BLUE)
        g.setColor(Color.BLUE);
        g.fillOval(screenRX - 5, screenRY - 5, 10, 10);

        // Labels
        g.setColor(Color.BLACK);
        g.drawString("Original (" + x + ", " + y + ")", screenX + 10, screenY);
        g.drawString("Reflected (" + reflectedX + ", " + reflectedY + ")", screenRX + 10, screenRY);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter x: ");
        double x = sc.nextDouble();

        System.out.print("Enter y: ");
        double y = sc.nextDouble();

        JFrame frame = new JFrame("Reflection about X-axis");
        ReflectionXAxis panel = new ReflectionXAxis(x, y);

        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
