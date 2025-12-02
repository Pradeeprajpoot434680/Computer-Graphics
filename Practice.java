import javax.swing.*;
import java.awt.*;

public class Practice extends JPanel {
  
    int[] point = {200,200,1};
    int[] rotatePoint(int[] p,int angle){
        double rad = Math.toRadians(angle);
        double sin = Math.sin(rad);
        double cos = Math.cos(rad);

      int[][] R = {
            {(int)Math.round(cos), (int)Math.round(-sin), 0},
            {(int)Math.round(sin), (int)Math.round(cos),  0},
            {0, 0, 1}
        };

         int[] result = new int[3];
            result[0] = p[0] * R[0][0] + p[1] * R[0][1] + p[2] * R[0][2]; // x'
            result[1] = p[0] * R[1][0] + p[1] * R[1][1] + p[2] * R[1][2]; // y'
            result[2] = 1;

        return result;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(point[0], point[1], 5, 5);
        
        int[] rotated = rotatePoint(point,45);

        g.setColor(Color.BLACK);
         g.fillRect(rotated[0]+point[0],point[1] +  rotated[1], 5, 5);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Practice panel = new Practice();
        frame.add(panel);
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Proper window close operation
        frame.setVisible(true);
    }
}
