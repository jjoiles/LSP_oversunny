package org.howard.edu.lsp.assignment3;

/**
 * Represents a single product row as read from the CSV file.
 * <p>
 * Immutable: once constructed, the fields cannot be changed.
 */
public final class Product {
  private final String id;
  private final String name;
  private final String category;
  private final double price;

  /**
   * Constructs a product instance.
   *
   * @param id       product identifier
   * @param name     product name (original case)
   * @param category category as read from input
   * @param price    numeric price
   */
  public Product(String id, String name, String category, double price) {
    this.id = id;
    this.name = name;
    this.category = category;
    this.price = price;
  }

  /** @return product ID */
  public String id() { return id; }

  /** @return product name (unmodified) */
  public String name() { return name; }

  /** @return product category */
  public String category() { return category; }

  /** @return product price as a double */
  public double price() { return price; }
}