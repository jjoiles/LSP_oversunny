package org.howard.edu.lsp.assignment2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

/**
 * ChatGpt was used as a guideline to correct and structure code. I did the code in segments based on tasks 
 * to be completed then I inserted the code that I wrote in Chatgpt to get corrections and guidelines as to what to tweak
 * to follow guidelines given and ensure my code performed a simple Extract-Transform-Load (ETL) process on product data.
 *One prompt asked was: Based on the instructions given compare my code and give guidelines or identify what is missing.
 */
public class ETLPipeline {

    public static void main(String[] args) {
        String inputFile = "data/products.csv";
        String outputFile = "data/transformed_products.csv";

        int rowsRead = 0;
        int rowsTransformed = 0;
        int rowsSkipped = 0;

        try {
            // === Extract Phase ===
            List<String> allLines = Files.readAllLines(Paths.get(inputFile));
            rowsRead = allLines.size() - 1;  // exclude header row

            // Prepare output list
            List<String> lines = new ArrayList<>();
            lines.add("ProductID,Name,Price,Category,PriceRange"); // header row

            // === Transform Phase ===
            if (allLines.size() > 1) { // more than just the header
                for (int i = 1; i < allLines.size(); i++) {
                    String[] fields = allLines.get(i).split(",");
                    if (fields.length < 4) { 
                        rowsSkipped++; // skip malformed rows
                        continue;
                    }
                    lines.add(transform(fields));
                    rowsTransformed++;
                }
            }

            // === Load Phase ===
            Files.write(Paths.get(outputFile), lines);

            // Print run summary
            System.out.println("ETL process completed!");
            System.out.println("Run Summary:");
            System.out.println(" - Rows read: " + rowsRead);
            System.out.println(" - Rows transformed: " + rowsTransformed);
            System.out.println(" - Rows skipped: " + rowsSkipped);
            System.out.println(" - Output written to: " + outputFile);

        } catch (IOException e) {
            System.out.println("Input file " + inputFile + " not found or could not be read.");
        }
    }

    /**
     * Transform method: applies transformation rules to a single row of product data.
     */
    private static String transform(String[] fields) {
        String id = fields[0];
        String name = fields[1].toUpperCase();
        double price = Double.parseDouble(fields[2]);
        String category = fields[3];

        // Apply 10% discount for Electronics
        if (category.equals("Electronics")) {
            price = Math.round(price * 0.9 * 100.0) / 100.0;
        }

        // Reclassify as Premium Electronics if discounted price > 500
        if (category.equals("Electronics") && price > 500) {
            category = "Premium Electronics";
        }

        // Assign price range
        String priceRange;
        if (price <= 10) {
            priceRange = "Low";
        } else if (price <= 100) {
            priceRange = "Medium";
        } else if (price <= 500) {
            priceRange = "High";
        } else {
            priceRange = "Premium";
        }

        return id + "," + name + "," + String.format("%.2f", price) + "," + category + "," + priceRange;
    }
}

