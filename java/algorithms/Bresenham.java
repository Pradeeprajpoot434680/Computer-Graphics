import javax.swing.*;
import java.awt.*;

class Bresenham extends JPanel{

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        int x0 = 1, y0 = 1 , xend = 400 , yend = 400;
        int dx = Math.abs(x0-xend);
        int dy = Math.abs(y0-yend);
        float pk = 2*dy - dx;
        int x = x0;
        int y = y0;
        g.fillRect(x,y,1,1);
        
        for(int i=0; i<dx; i++){
            if(pk<0){
                pk = pk + 2*dy;
                x = x + 1;
            }
            else{
                pk = pk + 2*dy - 2*dx;
                x = x + 1;
                y = y + 1;
            }
            g.fillRect(x,y,1,1);
        }
        
    }
    public static void main(String[] arg){
        
        JFrame frame = new JFrame();
        Bresenham panel = new Bresenham();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.add(panel);
        frame.setVisible(true);
    }
}