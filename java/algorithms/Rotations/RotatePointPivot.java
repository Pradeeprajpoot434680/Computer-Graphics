import java.util.Scanner;

public class RotatePointPivot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter point x: ");
        double x = sc.nextDouble();

        System.out.print("Enter point y: ");
        double y = sc.nextDouble();

        System.out.print("Enter pivot px: ");
        double px = sc.nextDouble();

        System.out.print("Enter pivot py: ");
        double py = sc.nextDouble();

        System.out.print("Enter angle in degrees: ");
        double angle = sc.nextDouble();

        // Convert degree → radian
        double rad = Math.toRadians(angle);

        // Translate point w.r.t pivot
        double translatedX = x - px;
        double translatedY = y - py;

        // Apply rotation
        double rotatedX = translatedX * Math.cos(rad) - translatedY * Math.sin(rad);
        double rotatedY = translatedX * Math.sin(rad) + translatedY * Math.cos(rad);

        // Translate back
        double finalX = rotatedX + px;
        double finalY = rotatedY + py;

        System.out.println("\nRotated Point:");
        System.out.println("x' = " + finalX);
        System.out.println("y' = " + finalY);
    }
}
