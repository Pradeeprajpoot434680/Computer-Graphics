import java.util.Scanner;

public class ScalingAboutFixedPoint {

    // Multiply 3x3 matrix with 3x1 vector
    public static double[] multiply(double[][] m, double[] v) {
        double[] result = new double[3];
        for (int i = 0; i < 3; i++) {
            result[i] = m[i][0] * v[0] + m[i][1] * v[1] + m[i][2] * v[2];
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input
        System.out.print("Enter x: ");
        double x = sc.nextDouble();

        System.out.print("Enter y: ");
        double y = sc.nextDouble();

        System.out.print("Enter fixed point xf: ");
        double xf = sc.nextDouble();

        System.out.print("Enter fixed point yf: ");
        double yf = sc.nextDouble();

        System.out.print("Enter scaling Sx: ");
        double sx = sc.nextDouble();

        System.out.print("Enter scaling Sy: ");
        double sy = sc.nextDouble();

        // 1. Translate fixed point to origin: T(-xf, -yf)
        double[][] T1 = {
            {1, 0, -xf},
            {0, 1, -yf},
            {0, 0, 1}
        };

        // 2. Scaling matrix
        double[][] S = {
            {sx, 0, 0},
            {0, sy, 0},
            {0, 0, 1}
        };

        // 3. Translate back: T(xf, yf)
        double[][] T2 = {
            {1, 0, xf},
            {0, 1, yf},
            {0, 0, 1}
        };

        // Initial point in homogeneous
        double[] point = {x, y, 1};

        // Apply transformations:  T2 * S * T1 * P
        point = multiply(T1, point);  
        point = multiply(S, point);   
        point = multiply(T2, point);  

        // Output
        System.out.println("\nScaled point about fixed point:");
        System.out.println("New X = " + point[0]);
        System.out.println("New Y = " + point[1]);
    }
}
