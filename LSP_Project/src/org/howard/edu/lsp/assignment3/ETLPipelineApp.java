package org.howard.edu.lsp.assignment3;

/**
 * Entry point for the ETL pipeline application.
 * <p>
 * This class wires together the extract–transform–load process
 * by delegating the logic to {@link ETLService}.
 * It then prints a summary report, preserving the exact format
 * from Assignment 2.
 */
public class ETLPipelineApp {
  /**
   * Main method. Runs the ETL pipeline on a given CSV file.
   *
   * @param args command-line arguments (unused)
   */
  public static void main(String[] args) {
    String inputFile = "data/products.csv";
    String outputFile = "data/transformed_products.csv";

    try {
      ETLService.Result r = ETLService.run(inputFile, outputFile);

      System.out.println("ETL process completed!");
      System.out.println("Run Summary:");
      System.out.println(" - Rows read: " + r.rowsRead());
      System.out.println(" - Rows transformed: " + r.rowsTransformed());
      System.out.println(" - Rows skipped: " + r.rowsSkipped());
      System.out.println(" - Output written to: " + outputFile);
    } catch (Exception e) {
      System.out.println("Input file " + inputFile + " not found or could not be read.");
    }
  }
}