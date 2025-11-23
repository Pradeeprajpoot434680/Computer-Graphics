import java.util.Scanner;

public class Rotation2D_Homogeneous {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Input original point
        System.out.print("Enter x: ");
        double x = sc.nextDouble();

        System.out.print("Enter y: ");
        double y = sc.nextDouble();

        // Input angle in degrees
        System.out.print("Enter angle (in degrees): ");
        double angle = sc.nextDouble();

        // Convert degree → radian
        double rad = Math.toRadians(angle);

        // Original point in homogeneous coordinates
        double[] point = { x, y, 1 };

        // 2D Rotation Matrix (3×3)
        double[][] R = {
            { Math.cos(rad), -Math.sin(rad), 0 },
            { Math.sin(rad),  Math.cos(rad), 0 },
            { 0,               0,            1 }
        };

        // Result vector
        double[] result = new double[3];

        // Matrix multiplication: result = R × point
        for (int i = 0; i < 3; i++) {
            result[i] = R[i][0] * point[0] +
                        R[i][1] * point[1] +
                        R[i][2] * point[2];
        }

        // Output final rotated point
        System.out.println("\n--- After 2D Rotation (Homogeneous Matrix) ---");
        System.out.println("New X = " + result[0]);
        System.out.println("New Y = " + result[1]);

        sc.close();
    }
}
