import java.util.Scanner;

public class Scaling2D_Homogeneous {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Input original point
        System.out.print("Enter x: ");
        double x = sc.nextDouble();

        System.out.print("Enter y: ");
        double y = sc.nextDouble();

        // Input scaling factors
        System.out.print("Enter Sx (scale in x-direction): ");
        double Sx = sc.nextDouble();

        System.out.print("Enter Sy (scale in y-direction): ");
        double Sy = sc.nextDouble();

        // Original point in homogeneous form (x, y, 1)
        double[] point = { x, y, 1 };

        // 2D Scaling Matrix (3×3)
        double[][] S = {
            { Sx, 0,  0 },
            { 0,  Sy, 0 },
            { 0,  0,  1 }
        };

        // Result vector
        double[] result = new double[3];

        // Matrix multiplication: result = S × point
        for (int i = 0; i < 3; i++) {
            result[i] = S[i][0] * point[0] +
                        S[i][1] * point[1] +
                        S[i][2] * point[2];
        }

        // Output final scaled point
        System.out.println("\n--- After 2D Scaling (Homogeneous Matrix) ---");
        System.out.println("New X = " + result[0]);
        System.out.println("New Y = " + result[1]);

        sc.close();
    }
}
