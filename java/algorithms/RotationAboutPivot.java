import java.util.Scanner;

public class RotationAboutPivot {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Input original point
        System.out.print("Enter x: ");
        double x = sc.nextDouble();

        System.out.print("Enter y: ");
        double y = sc.nextDouble();

        // Input pivot point
        System.out.print("Enter pivot x (xp): ");
        double xp = sc.nextDouble();

        System.out.print("Enter pivot y (yp): ");
        double yp = sc.nextDouble();

        // Input angle
        System.out.print("Enter angle (in degrees): ");
        double angle = sc.nextDouble();

        // Convert degrees → radians
        double rad = Math.toRadians(angle);

        // Rotation about pivot point
        double newX = xp + (x - xp) * Math.cos(rad) - (y - yp) * Math.sin(rad);
        double newY = yp + (x - xp) * Math.sin(rad) + (y - yp) * Math.cos(rad);

        // Output result
        System.out.println("\n--- After Rotation About Pivot ---");
        System.out.println("New X = " + newX);
        System.out.println("New Y = " + newY);

        sc.close();
    }
}
