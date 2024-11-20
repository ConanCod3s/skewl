import java.text.DecimalFormat;
import java.util.Random;

public class Project3 {
    public static void main(String[] args) {
        Random rand = new Random();

        double x = rand.nextDouble() * 10;    // Random x between 0 and 10
        double y = rand.nextDouble() * 10;    // Random y between 0 and 10
        double radius = 1 + rand.nextDouble() * 9; // Random radius between 1 and 10

        Circle2D c1 = new Circle2D(x, y, radius);

        Circle2D c2 = new Circle2D(rand.nextDouble()
                * 10, rand.nextDouble()
                * 10, 1 + rand.nextDouble() * 9);
        Circle2D c3 = new Circle2D(rand.nextDouble()
                * 10, rand.nextDouble()
                * 10, 1 + rand.nextDouble() * 9);

        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("Main Circle (c1) with random values:");
        System.out.println(
                "x: " + df.format(c1.getX()) +
                        ", y: " + df.format(c1.getY()) +
                        ", radius: " + df.format(c1.getRadius()));
        System.out.println("Area: " + df.format(c1.getArea()));
        System.out.println("Perimeter: " + df.format(c1.getPerimeter()));

        System.out.println("\nTest Circle (c2):");
        System.out.println(
                "x: " + df.format(c2.getX()) +
                        ", y: " + df.format(c2.getY()) +
                        ", radius: " + df.format(c2.getRadius()));

        System.out.println("\nTest Circle (c3):");
        System.out.println(
                "x: " + df.format(c3.getX()) +
                        ", y: " + df.format(c3.getY()) +
                        ", radius: " + df.format(c3.getRadius()));

        System.out.println("\nc1.contains(c2): " + c1.contains(c2));
        System.out.println("c1.overlaps(c3): " + c1.overlaps(c3));
    }
}

class Circle2D {
    private double x;
    private double y;
    private double radius;

    public Circle2D() {
        this(0, 0, 1);
    }

    public Circle2D(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    public boolean contains(double x, double y) {
        double distance = Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2));
        return distance < radius;
    }

    public boolean contains(Circle2D circle) {
        double distance = Math.sqrt(Math.pow(circle.getX() - this.x, 2) + Math.pow(circle.getY() - this.y, 2));
        return distance + circle.getRadius() <= this.radius;
    }

    public boolean overlaps(Circle2D circle) {
        double distance = Math.sqrt(Math.pow(circle.getX() - this.x, 2) + Math.pow(circle.getY() - this.y, 2));
        return distance < this.radius + circle.getRadius();
    }
}