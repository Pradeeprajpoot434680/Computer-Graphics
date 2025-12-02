import java.util.*;

public class CohenSutherland {

    // Region codes
    static final int INSIDE = 0; // 0000
    static final int LEFT   = 1; // 0001
    static final int RIGHT  = 2; // 0010
    static final int BOTTOM = 4; // 0100
    static final int TOP    = 8; // 1000

    // Clipping window boundaries
    static double xmin = 0, ymin = 0, xmax = 10, ymax = 8;

    // Function to compute region code for a point(x,y)
    static int computeCode(double x, double y) {
        int code = INSIDE;

        if (x < xmin)      code |= LEFT;
        else if (x > xmax) code |= RIGHT;

        if (y < ymin)      code |= BOTTOM;
        else if (y > ymax) code |= TOP;

        return code;
    }

    // Cohen-Sutherland clipping algorithm
    static void cohenSutherlandClip(double x1, double y1, double x2, double y2) {

        int code1 = computeCode(x1, y1);
        int code2 = computeCode(x2, y2);
        boolean accept = false;

        while (true) {
            // Case 1: both points inside
            if (code1 == 0 && code2 == 0) {
                accept = true;
                break;
            }
            // Case 2: both points outside in same region → trivially reject
            else if ((code1 & code2) != 0) {
                break;
            }
            else {
                // Clipping needed
                int outCode;
                double x = 0, y = 0;

                // Pick the point outside the window
                outCode = (code1 != 0) ? code1 : code2;

                // Find intersection
                if ((outCode & TOP) != 0) { // above window
                    x = x1 + (x2 - x1) * (ymax - y1) / (y2 - y1);
                    y = ymax;
                } else if ((outCode & BOTTOM) != 0) { // below window
                    x = x1 + (x2 - x1) * (ymin - y1) / (y2 - y1);
                    y = ymin;
                } else if ((outCode & RIGHT) != 0) { // right of window
                    y = y1 + (y2 - y1) * (xmax - x1) / (x2 - x1);
                    x = xmax;
                } else if ((outCode & LEFT) != 0) { // left of window
                    y = y1 + (y2 - y1) * (xmin - x1) / (x2 - x1);
                    x = xmin;
                }

                // Replace point with intersection point
                if (outCode == code1) {
                    x1 = x;
                    y1 = y;
                    code1 = computeCode(x1, y1);
                } else {
                    x2 = x;
                    y2 = y;
                    code2 = computeCode(x2, y2);
                }
            }
        }

        if (accept) {
            System.out.println("\nLine accepted from (" + x1 + ", " + y1 +
                               ") to (" + x2 + ", " + y2 + ")");
        } else {
            System.out.println("\nLine rejected.");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter line endpoints:");
        System.out.print("x1: "); double x1 = sc.nextDouble();
        System.out.print("y1: "); double y1 = sc.nextDouble();
        System.out.print("x2: "); double x2 = sc.nextDouble();
        System.out.print("y2: "); double y2 = sc.nextDouble();

        System.out.println("\nClipping Window:");
        System.out.println("xmin=0, ymin=0, xmax=10, ymax=8");

        cohenSutherlandClip(x1, y1, x2, y2);

        sc.close();
    }
}
