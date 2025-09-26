Reflection – Assignment 3 (OO ETL)
Changes from A2 to A3

In Assignment 2 (A2), everything—extract, transform, and load—was grouped into a single class. In Assignment 3 (A3), I redesigned the pipeline to follow an object-oriented structure:

ETLPipelineApp: Handles entry point and summary reporting.

ETLService: Contains the ETL logic.

Product: Represents each data row.

This shift improved several areas:

Encapsulation: Each class now has a single, clear responsibility.

Testability: I can call ETLService.run directly in tests, verifying row counts and outputs.

Extensibility: Adding new sources (like JSON or databases) or transformations (like taxes or currency conversion) is easier, since changes are isolated in ETLService or helper methods.

Object-Oriented Concepts Applied

Classes/Objects: Product models the data; ETLPipelineApp and ETLService organize behavior.

Abstraction: The pipeline exposes a high-level run(input, output) interface, hiding file handling and transformation details.

Encapsulation: Parsing, validation, and CSV writing are tucked inside ETLService, with results exposed only through a summary.

Immutability: Product is immutable, which reduces accidental state changes and makes reasoning about the code easier.

Preserving Correctness

To ensure consistency with A2, I kept the rules and order the same:

Uppercase the product name.

Apply a 10% discount to Electronics.

Reclassify to Premium Electronics if the discounted price exceeds 500.

Assign a price range bucket.

Other safeguards include:

Same I/O contract: Input file (data/products.csv), output file (data/transformed_products.csv), and identical header line.

Row counting: rowsRead excludes the header; malformed lines are skipped.

Formatting: Prices always appear with two decimals using String.format("%.2f", price).

Edge Cases and Design Choices

Malformed price: If the price isn’t numeric, the row is skipped without crashing.

Short lines: Rows with fewer than four fields are also skipped.

CSV commas: Assumes no quoted commas inside fields (same as A2). If needed, this could be replaced with a CSV library.

Future Improvements (Beyond A3 Requirements)

Replace naive string splitting with a proper CSV parser.

Add unit tests comparing A2 vs. A3 outputs for identical inputs.

Make thresholds (discount rate, premium cutoff, etc.) configurable rather than hard-coded.