package org.example;
import java.util.Hashtable;
import java.util.Scanner;

public class Main {

    // Method to calculate the dimensions and cost of painting
    public static double[] Dimensions(String Type) {
        // Define a Hashtable to store color prices
        Hashtable<String, Double> colours = new Hashtable<>();
        // Populate the Hashtable with color prices
        colours.put("red", 2.50);
        colours.put("brown", 2.30);
        colours.put("black", 1.96);
        colours.put("White", 2.90);
        colours.put("Blue", 2.21);
        colours.put("Pink", 2.60);

        double Dim = 0; // Initialize total dimension and price
        double price = 0;

        Scanner reader = new Scanner(System.in); // Create a scanner object for user input
        System.out.println("How many " + Type +"s  are there?: "); // Prompt user for the number of surfaces
        int surface;
        try {
            surface = reader.nextInt(); // Read user input
        } catch (Exception e) {
            System.out.println("Invalid input for number of " + Type + "s. Please enter a valid integer.");
            return new double[] {0, 0}; // Exit method if input is invalid
        }

        // Loop through each surface
        for (int i = 1 ; i <= surface; i++) {
            double col = 2.3; // Default colour price

            // Prompt user for height of the surface
            System.out.println("What is the height of " + Type + " " + i + " in mm?: ");
            double height;
            try {
                height = reader.nextDouble(); // Read user input for height
            } catch (Exception e) {
                System.out.println("Invalid input for height. Please enter a valid number.");
                return new double[] {0, 0}; // Exit method if input is invalid
            }

            // Prompt user for length of the surface
            System.out.println("What is the length of " + Type + " " + i + " in mm?: ");
            double length;
            try {
                length = reader.nextDouble(); // Read user input for length
            } catch (Exception e) {
                System.out.println("Invalid input for length. Please enter a valid number.");
                return new double[] {0, 0}; // Exit method if input is invalid
            }

            // If the surface is a wall, ask for color preference and calculate cost based on color
            if (Type.equals("wall")) {
                System.out.println("What colour would you like this wall?: ");
                String colour = reader.next(); // Read user input for color preference
                if (colours.containsKey(colour.toLowerCase())) {
                    col = colours.get(colour.toLowerCase()); // Get the price of the selected color
                } else {
                    System.out.println("We do not have data for the " + colour +
                            " color and so a basic colour price will be used.");
                }
            }

            // Calculate the area of the surface and add it to the total dimension cost
            Dim += (height / 1000) * (length / 1000);
            price = Dim * col;

        }
        return new double[] {Dim, price}; // Return the total dimension cost
    }

    // Main method
    public static void main(String[] args) {

        String Type = "wall"; // Default surface type
        double cost = 0;
        double SurfaceArea = 0;

        Scanner reader = new Scanner(System.in);

        // Check if user is painting any walls
        System.out.println("Are you painting any walls?: ");
        String wall = reader.next();
        if (wall.toLowerCase().equals("yes") || wall.toLowerCase().equals("y")) {
            double[] result = Dimensions(Type); // Calculate cost for walls
            cost += result[1];
            SurfaceArea += result[0];
        }

        // Ask if there are any windows or doors in the way
        System.out.println("Are there any windows or doors in the way?: ");
        String obst = reader.next();
        double obstruct = 0;
        if (obst.toLowerCase().equals("yes") || obst.toLowerCase().equals("y")) {
            Type = "obstacle"; // Change surface type to obstacle
            double[] result = Dimensions(Type); // Calculate cost for walls
            cost -= result[1];
            SurfaceArea -= result[0]; // Calculate cost for obstacles
        }

        // Ask if user is painting any ceilings
        System.out.println("Are you painting any ceilings?: ");
        String ceil = reader.next();
        if (ceil.toLowerCase().equals("yes") || ceil.toLowerCase().equals("y")) {
            Type = "ceiling"; // Change surface type to ceiling
            double[] result = Dimensions(Type); // Calculate cost for walls
            cost += result[1];
            SurfaceArea += result[0]; // Calculate cost for ceilings
        }

        // Calculate total price by subtracting obstacle cost

        // Print total price
        System.out.printf("You have to paint %.2f square meters of wall and" +
                " the paint will cost you £%.2f. \n", SurfaceArea, cost );

        // Calculate the number of paint cans required
        double litres = SurfaceArea/6;
        double big = Math.floor(litres/5);
        double med = Math.floor((litres - (big*5))/ 2);
        double small = (litres - (big*5) - (med*2));
        small = Math.ceil(small);

        // Print the number of paint cans required
        System.out.println("This means you will need " + litres + " litres of paint and so should buy " + big +
                " Large cans (5L), " + med + " medium cans (2L)" +
                " and " + small + " small cans (1L)");

        reader.close();
    }
}