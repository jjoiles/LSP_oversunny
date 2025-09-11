## Assumptions
1. The CSV file is named **`products.csv`** and is stored inside a folder called **`data/`** at the root of the project.
2. The CSV contains a **header row** with the following columns in order:  
   - `ProductID` (integer)  
   - `Name` (string)  
   - `Price` (decimal number)  
   - `Category` (string)  
3. The CSV does not contain commas inside quoted fields (e.g., `"Laptop, Pro"`). If such cases arise, a library like **OpenCSV** or **Apache Commons CSV** should be used.
4. Data integrity is assumed, meaning each row follows the correct data types. Invalid rows (e.g., missing values or incorrect formats) are not handled in this basic implementation.
5. The program is designed for small CSV files that can be read comfortably into memory using a buffered reader.

## Brief Design Notes
- The program uses **BufferedReader** and **FileReader** from the Java standard library to process the CSV file line by line.  
- The first line is treated as a **header** and displayed separately.  
- Each subsequent line is **split by commas** into fields.  
- The values are **parsed into their respective data types** (`int`, `String`, `double`, `String`).  
- The program prints the structured product information to the console.  
- This follows a **simple ETL process**:  
  - **Extract** → Read raw CSV data.  
  - **Transform** → Parse and convert values into appropriate Java data types.  
  - **Load** → Output transformed data to the console (in a real scenario, this could be a database or another storage system).

## How to Run
1. Place your `products.csv` file in the `data/` directory.

AI/Internet Documentation

How AI was used:

ChatGPT was used as a guideline to correct and structure code.

The code was written in segments based on tasks to be completed, then reviewed through ChatGPT for corrections and improvements.

ChatGPT provided guidelines and tweaks to ensure the code followed a simple Extract-Transform-Load (ETL) pattern.

Example prompt:

"Based on the instructions given compare my code and give guidelines or identify what is missing."

Internet references:

General Java documentation for BufferedReader and FileReader usage.

CSV structure examples for testing.
3. Compile the program from the `src/` directory:
   ```bash
   javac CSVReaderExample.java
