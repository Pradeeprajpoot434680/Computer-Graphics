import java.util.Scanner;

public class TranslationMatrix2D {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Input original point
        System.out.print("Enter x: ");
        int x = sc.nextInt();

        System.out.print("Enter y: ");
        int y = sc.nextInt();

        // Input translation values
        System.out.print("Enter Tx: ");
        int Tx = sc.nextInt();

        System.out.print("Enter Ty: ");
        int Ty = sc.nextInt();

        // Homogeneous coordinates (x, y, 1)
        int[] point = {x, y, 1};

        // Translation matrix (3×3)
        int[][] T = {
            {1, 0, Tx},
            {0, 1, Ty},
            {0, 0,  1}
        };

        // Resultant point
        int[] result = new int[3];

        // Matrix multiplication : result = T × point
        for (int i = 0; i < 3; i++) {
            result[i] = T[i][0] * point[0] +
                        T[i][1] * point[1] +
                        T[i][2] * point[2];
        }

        // Output
        System.out.println("\n--- After Translation (Matrix Method) ---");
        System.out.println("New X = " + result[0]);
        System.out.println("New Y = " + result[1]);

        sc.close();
    }
}
