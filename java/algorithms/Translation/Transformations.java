
import javax.swing.*;
import java.awt.*;

public class Transformations extends JPanel {

    // The original point that will be transformed
    int[] point = {100, 100, 1};

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.RED);   

        // Draw the original point
        g.fillOval(point[0], point[1], 10, 10);

        // Apply transformation and get the new coordinates
        int[] transformedPoint = applyTransformation(point, 200, 100); // Translation by (200, 100)

        // Draw the transformed point
        g.setColor(Color.BLUE);
        g.fillOval(transformedPoint[0], transformedPoint[1], 10, 10);
    }

    // Apply a 2D transformation to a point (translation in this case)
    int[] applyTransformation(int[] point, int tx, int ty) {
        int[][] transformationMatrix = {
            {1, 0, tx}, // X translation
            {0, 1, ty}, // Y translation
            {0, 0, 1}   // Homogeneous coordinate for transformation
        };

        // Multiply the point by the transformation matrix
        int[] result = multiply(point, transformationMatrix);

        return result;
    }

    // Multiply a point with a transformation matrix
    int[] multiply(int[] point, int[][] transformationMatrix) {
        int[] result = new int[3];
        
        // Perform the matrix multiplication
        for (int i = 0; i < 3; i++) {
            result[i] = 0;
            for (int j = 0; j < 3; j++) {
                result[i] += transformationMatrix[i][j] * point[j];
            }
        }

        // Return the transformed point (x, y)
        return new int[]{result[0], result[1]}; 
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Transformation Java Graphics");
        Transformations t = new Transformations();
        f.add(t);
        f.setSize(1000, 1000);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
