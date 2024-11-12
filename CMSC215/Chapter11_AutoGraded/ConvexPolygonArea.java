import java.util.Random;
import java.util.Scanner;

public class ConvexPolygonArea {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Enter the number of points: ");
        int n = scanner.nextInt();

        double[] x = new double[n];
        double[] y = new double[n];

        System.out.println("Enter the coordinates of the points (x y for each point or 'R' to randomly generate points):");
        scanner.nextLine();
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("R")) {
            System.out.println("Randomly generated coordinates of the points:");
            for (int i = 0; i < n; i++) {
                x[i] = -10.0 + (20.0 * random.nextDouble());
                y[i] = -10.0 + (20.0 * random.nextDouble());
                System.out.printf("Point %d: (%.2f, %.2f)%n", i + 1, x[i], y[i]);
            }
        } else {
            Scanner pointScanner = new Scanner(input + " " + scanner.nextLine());
            for (int i = 0; i < n; i++) {
                x[i] = pointScanner.nextDouble();
                y[i] = pointScanner.nextDouble();
            }
            pointScanner.close();
        }
        double area = calculatePolygonArea(x, y, n);

        System.out.printf("The total area is %.2f%n", area);
        scanner.close();
    }

    public static double calculatePolygonArea(double[] x, double[] y, int n) {
        double area = 0;

        for (int i = 0; i < n; i++) {
            int nextIndex = (i + 1) % n;
            area += (x[i] * y[nextIndex]) - (y[i] * x[nextIndex]);
        }

        area = Math.abs(area) / 2.0;

        return area;
    }
}
