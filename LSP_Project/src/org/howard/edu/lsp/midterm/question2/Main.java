package org.howard.edu.lsp.midterm.question2;

public class Main {
    public static void main(String[] args) {
        // EXACT required output lines:
        System.out.println("Circle radius 3.0 \u2192 area = " + AreaCalculator.area(3.0));
        System.out.println("Rectangle 5.0 x 2.0 \u2192 area = " + AreaCalculator.area(5.0, 2.0));
        System.out.println("Triangle base 10, height 6 \u2192 area = " + AreaCalculator.area(10, 6));
        System.out.println("Square side 4 \u2192 area = " + AreaCalculator.area(4));

        // Exception demo
        try {
            System.out.println(AreaCalculator.area(-1.0)); // triggers IllegalArgumentException
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /*
     * Overloading vs. separate method names:
     * Using overloading keeps a cohesive API under a single concept name ("area"),
     * improving discoverability and consistent usage while dispatching by signature.
     * Separate names (circleArea, rectangleArea, etc.) work but fragment the API and
     * make it more verbose without adding clarity.
     */
}