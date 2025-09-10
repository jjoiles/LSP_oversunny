package org.howard.edu.lsp.assignment2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

public class ETLPipeline {

	public static void main(String[] args) {
		String inputFile = "products.csv";
        String outputFile = "transformed_products.csv";
        
        try {
            List<String> allLines = Files.readAllLines(Paths.get(inputFile));
            List<String> lines = new ArrayList<>();
            lines.add("ProductID,Name,Price,Category,PriceRange");
            
            for (int i = 1; i < allLines.size(); i++) {
                String[] fields = allLines.get(i).split(",");
                lines.add(transform(fields));
            }
            
            Files.write(Paths.get(outputFile), lines);
            System.out.println("ETL process completed!");
        } catch (IOException e) {
            System.out.println("Error reading or writing file: " + e.getMessage());

	}

}
	
	private static String transform(String[] fields) {
        String id = fields[0];
        String name = fields[1].toUpperCase();
        double price = Double.parseDouble(fields[2]);
        String category = fields[3];

        if (category.equals("Electronics")) {
            price = Math.round(price * 0.9 * 100.0) / 100.0;
        }
        
        if (category.equals("Electronics") && price > 500) {
            category = "Premium Electronics";
        }
        
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
