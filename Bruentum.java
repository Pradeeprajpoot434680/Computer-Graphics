import javax.swing.*;
import java.awt.*;  

class Bruentum extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        int x1 = 1, y1 = 1, x2 = 400, y2 = 400;
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
   
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        
        int err = dx - dy;

        while (true) {
            g.fillRect(x1, y1, 1, 1);

            if (x1 == x2 && y1 == y2)
                break;

            int e2 = 2 * err;

            if (e2 > -dy) {
                err = err - dy;
                x1 = x1 + sx;
            }

            if (e2 < dx) {
                err = err + dx;

                y1 = y1 + sy;
            }
        }
        
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bruentum");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.add(new Bruentum());
        frame.setVisible(true);
    }
}


