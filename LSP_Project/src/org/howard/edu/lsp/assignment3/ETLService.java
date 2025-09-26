package org.howard.edu.lsp.assignment3;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides the end-to-end ETL service.
 * <p>
 * Handles extraction from CSV, transformation rules, and writing
 * the transformed file back to disk.
 */
public final class ETLService {

  /**
   * Runs the complete ETL process:
   * <ul>
   *   <li><b>Extract:</b> Reads products from CSV (skips malformed rows).</li>
   *   <li><b>Transform:</b> Applies discount, reclassification,
   *       upper-casing, and price-range logic.</li>
   *   <li><b>Load:</b> Writes transformed lines back to CSV.</li>
   * </ul>
   * Behavior and output format match Assignment 2.
   *
   * @param inputFile  path to the input CSV file (expects header row)
   * @param outputFile path to the transformed CSV file
   * @return a {@link Result} object containing row counts
   * @throws Exception if input cannot be read or output cannot be written
   */
  public static Result run(String inputFile, String outputFile) throws Exception {
    // === Extract ===
    List<String> allLines = Files.readAllLines(Paths.get(inputFile));
    int rowsRead = Math.max(0, allLines.size() - 1); // exclude header
    List<Product> products = new ArrayList<>();

    if (allLines.size() > 1) {
      for (int i = 1; i < allLines.size(); i++) {
        String line = allLines.get(i);
        if (line == null || line.isEmpty()) continue;

        // Keep empty trailing fields; simple split (assumes no quoted commas)
        String[] f = line.split(",", -1);
        if (f.length < 4) continue;

        String id = f[0].trim();
        String name = f[1].trim();
        String priceStr = f[2].trim();
        String category = f[3].trim();

        try {
          double price = Double.parseDouble(priceStr);
          products.add(new Product(id, name, category, price));
        } catch (NumberFormatException ignore) {
          // malformed price → effectively skipped (do not add)
        }
      }
    }

    // === Transform ===
    List<String> out = new ArrayList<>();
    out.add("ProductID,Name,Price,Category,PriceRange"); // header

    int transformed = 0;
    for (Product p : products) {
      String nameUp = p.name().toUpperCase();
      double price = p.price();
      String category = p.category();

      // 10% discount for Electronics
      if ("Electronics".equalsIgnoreCase(category)) {
        price = Math.round(price * 0.9 * 100.0) / 100.0; // 2 dp
      }

      // Reclassify as Premium Electronics if discounted price > 500
      if ("Electronics".equalsIgnoreCase(category) && price > 500.0) {
        category = "Premium Electronics";
      }

      // Price range buckets
      String priceRange =
          (price <= 10.0) ? "Low" :
          (price <= 100.0) ? "Medium" :
          (price <= 500.0) ? "High" : "Premium";

      out.add(p.id() + "," + nameUp + "," + String.format("%.2f", price) + "," + category + "," + priceRange);
      transformed++;
    }

    // === Load ===
    Files.write(Paths.get(outputFile), out);

    int skipped = rowsRead - transformed;
    return new Result(rowsRead, transformed, skipped);
  }

  /**
   * Immutable record holding counts of rows processed.
   *
   * @param rowsRead        number of rows read from input (excluding header)
   * @param rowsTransformed number of rows successfully transformed
   * @param rowsSkipped     number of malformed or skipped rows
   */
  public static record Result(int rowsRead, int rowsTransformed, int rowsSkipped) {}
}
