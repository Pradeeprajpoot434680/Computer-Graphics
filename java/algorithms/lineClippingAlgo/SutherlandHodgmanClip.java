package lineClippingAlgo;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class SutherlandHodgmanClip extends JPanel {

    // Clipping Window
    int xMin = 100, yMin = 100;
    int xMax = 300, yMax = 250;

    // Polygon to clip (You can change values)
    int[][] polygon = {
        {50, 150},
        {150, 50},
        {350, 150},
        {250, 300},
        {100, 300}
    };

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sutherland–Hodgman Polygon Clipping");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new SutherlandHodgmanClip());
        frame.setSize(600, 500);
        frame.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Draw clipping window
        g.setColor(Color.RED);
        g.drawRect(xMin, yMin, xMax - xMin, yMax - yMin);

        // Draw original polygon (BLUE)
        g.setColor(Color.BLUE);
        drawPolygon(g, polygon);

        // Clip the polygon
        int[][] clipped = clipPolygon(polygon);

        // Draw clipped polygon (GREEN)
        g.setColor(Color.GREEN);
        drawPolygon(g, clipped);
    }

    // Draw Polygon using lines
    void drawPolygon(Graphics g, int[][] poly) {
        for (int i = 0; i < poly.length; i++) {
            int x1 = poly[i][0];
            int y1 = poly[i][1];
            int x2 = poly[(i + 1) % poly.length][0];
            int y2 = poly[(i + 1) % poly.length][1];
            g.drawLine(x1, y1, x2, y2);
        }
    }

    // Sutherland–Hodgman Polygon Clipping main function
    int[][] clipPolygon(int[][] poly) {
        poly = clip(poly, "LEFT");
        poly = clip(poly, "RIGHT");
        poly = clip(poly, "BOTTOM");
        poly = clip(poly, "TOP");
        return poly;
    }

    boolean insideLeft(int x) { return x >= xMin; }
    boolean insideRight(int x) { return x <= xMax; }
    boolean insideBottom(int y) { return y >= yMin; }
    boolean insideTop(int y) { return y <= yMax; }

    // Intersection with a particular clipping edge
    int[] intersect(int[] A, int[] B, String edge) {
        int x1 = A[0], y1 = A[1];
        int x2 = B[0], y2 = B[1];

        double m = (double)(y2 - y1) / (x2 - x1 + 0.00001);

        int x = 0, y = 0;

        switch (edge) {
            case "LEFT":
                x = xMin;
                y = y1 + (int)(m * (xMin - x1));
                break;
            case "RIGHT":
                x = xMax;
                y = y1 + (int)(m * (xMax - x1));
                break;
            case "BOTTOM":
                y = yMin;
                x = x1 + (int)((yMin - y1) / m);
                break;
            case "TOP":
                y = yMax;
                x = x1 + (int)((yMax - y1) / m);
                break;
        }

        return new int[]{x, y};
    }

    // Clip polygon against one edge
    int[][] clip(int[][] poly, String edge) {
        ArrayList<int[]> output = new ArrayList<>();

        for (int i = 0; i < poly.length; i++) {
            int[] A = poly[i];
            int[] B = poly[(i + 1) % poly.length];

            boolean A_inside = isInside(A, edge);
            boolean B_inside = isInside(B, edge);

            if (A_inside && B_inside) {
                output.add(B);
            } else if (A_inside && !B_inside) {
                output.add(intersect(A, B, edge));
            } else if (!A_inside && B_inside) {
                output.add(intersect(A, B, edge));
                output.add(B);
            }
        }

        return output.toArray(new int[output.size()][]);
    }

    // Check inside for each edge
    boolean isInside(int[] P, String edge) {
        switch (edge) {
            case "LEFT":   return insideLeft(P[0]);
            case "RIGHT":  return insideRight(P[0]);
            case "BOTTOM": return insideBottom(P[1]);
            case "TOP":    return insideTop(P[1]);
        }
        return false;
    }
}
