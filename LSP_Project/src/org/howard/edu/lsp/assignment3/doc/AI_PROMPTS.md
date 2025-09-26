AI Prompts – Assignment 3

For this assignment, I used AI mainly as a guide to structure my code and double-check that I was following the requirements. Below are a few representative prompts and the kind of guidance I got from them.

Prompt 1 – OO refactor while preserving output

What I asked:
I asked the AI to help me refactor my original procedural ETL into a cleaner object-oriented design with three classes (an entry point, a service class, and a data model). I made sure to specify that the new version had to keep the exact same CSV output and console summary from A2, since preserving correctness was critical.

What I took from it:
The AI confirmed a good structure:

ETLPipelineApp as the entry point.

ETLService with a run(input, output) method that handles the actual ETL process.

An immutable Product class to model each row.

This gave me a clear blueprint to start from.

Prompt 2 – Confirm rule order and formatting

What I asked:
I wanted to make sure I wasn’t accidentally changing the logic. So I asked the AI to verify that my transformations were still happening in the right order and that the price formatting matched A2.

What I took from it:
The guidance emphasized applying the discount before reclassification and reminded me to keep using String.format("%.2f", price) for consistency. This helped me avoid subtle mistakes.

Prompt 3 – Handling malformed rows

What I asked:
I wanted to know the best way to deal with rows that had missing fields or invalid numbers without breaking the program.

What I took from it:
The AI suggested using split(",", -1) and trimming fields, then skipping rows with fewer than 4 fields or bad prices. It also pointed out that skipped rows should still be counted in the summary, which helped me keep my reporting accurate.

Prompt 4 – Adding Javadocs

What I asked:
Since documentation was part of the requirements, I asked how to add clear and concise Javadocs to each class and method.

What I took from it:
The guidance reminded me to document the purpose of each class, the parameters and return values for methods, and any exceptions that might be thrown. This made my code much easier to read and grade.