import javax.swing.*;
import java.awt.*;


class LineDDA extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Starting and ending coordinates for the line
        int x0 = 50, y0 = 50, xend = 50, yend = 677;
        int dx, dy;
        float x, y, steps, xinc, yinc;

        // Calculate differences in x and y
        dx = xend - x0;
        dy = yend - y0;
        x = x0;
        y = y0;

        // Determine number of steps required
        steps = Math.max(Math.abs(dx), Math.abs(dy));

        // Calculate the increment for x and y using floating-point division
        xinc = (float) dx / steps;
        yinc = (float) dy / steps;

        // Plot the first pixel
        g.fillRect(x0, y0, 1, 1);

        // Loop to plot the remaining pixels
        for (int i = 0; i < steps; i++) {
            x += xinc;
            y += yinc;

            // Cast x and y to int to plot the pixel at the correct location
            g.fillRect((int) x, (int) y, 1, 1);
        }
    }

    public static void main(String[] args) {
        // Set up the JFrame and add the LineDDA JPanel
        JFrame frame = new JFrame("Line DDA Algorithm");
        LineDDA panel = new LineDDA();
        frame.add(panel);
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
