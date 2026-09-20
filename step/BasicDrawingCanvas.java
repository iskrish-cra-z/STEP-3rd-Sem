/**
 * Problem 1: Basic Drawing Canvas
 * Week 7 - Category A Assignment
 */
abstract class Shape {
    public final String shapeId;
    private static int counter = 0;

    public Shape() {
        counter++;
        this.shapeId = "SHP-" + (1000 + counter);
    }

    public abstract double calculateArea();

    // Compile-time polymorphism via overloading
    public void scale(double factor) {
        scale(factor, factor);
    }

    public void scale(double xFactor, double yFactor) {
        // Subclasses should override this to scale their own fields
    }

    public String getShapeId() {
        return shapeId;
    }
}

class CircleShape extends Shape {
    private double radius;

    public CircleShape(double radius) {
        super();
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void scale(double xFactor, double yFactor) {
        // For a circle, use xFactor to scale uniformly
        radius *= xFactor;
    }
}

class SquareShape extends Shape {
    private double side;

    public SquareShape(double side) {
        super();
        this.side = side;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }

    @Override
    public void scale(double xFactor, double yFactor) {
        side *= xFactor; // For square, both factors could be applied distinctly; using xFactor for side
    }
}

public class BasicDrawingCanvas {
    static void printArea(Shape s) {
        // Polymorphic dispatch — no subclass check
        System.out.println(s.calculateArea());
    }

    public static void main(String[] args) {
        CircleShape c = new CircleShape(5.0);
        System.out.printf("%.2f%n", c.calculateArea()); // ~78.54

        SquareShape sq = new SquareShape(4.0);
        System.out.println(sq.calculateArea()); // 16.0

        sq.scale(2.0); // one-argument overload — side becomes 8
        System.out.println(sq.calculateArea()); // 64.0

        printArea(c);
    }
}
