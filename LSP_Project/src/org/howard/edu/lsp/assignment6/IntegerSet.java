package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;

import java.util.List;

public class IntegerSet {
    // Store the set elements in an ArrayList.
    private List<Integer> set = new ArrayList<Integer>();

    // Default Constructor
    public IntegerSet() {
    }

    /**
     * Constructor that initializes the set with given values.
     * @param set the initial values for the set
     */    
    public IntegerSet(ArrayList<Integer> set) {
        this.set = set;
    }

    // Clears the internal representation of the set
    public void clear() {
        set.clear();
    }

    /**
     * Returns the number of elements in the set.
     * @return the length of the set
     */
    public int length() {
        return set.size();
    }

    /*
     * Returns true if the 2 sets are equal, false otherwise;
     * Two sets are equal if they contain all of the same values in ANY order.
     */
    /**
     * Checks if two sets are equal.
     * @param o the other set to compare
     * @return true if the sets are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntegerSet that = (IntegerSet) o;
        return set.containsAll(that.set) && that.set.containsAll(set);
    }

 // Returns true if the set contains the value, otherwise false.
    /**
     * Checks if the set contains a specific value.
     * @param value the value to check
     * @return true if the value is in the set, false otherwise
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    // Returns the largest item in the set or throws IllegalStateException if empty.
    /**
     * Returns the largest element in the set or throws IllegalStateException if empty.
     * @return the largest integer or IllegalStateException thrown if empty
     */
    public int largest() {
        if (set.isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }
        int largest = set.get(0);
        for (int num : set) {
            if (num > largest) {
                largest = num;
            }
        }
        return largest;
    }

    // Returns the smallest item in the set or throws IllegalStateException if empty.
    /**
     * Returns the smallest element in the set or throws IllegalStateException if empty..
     * @return the smallest integer or IllegalStateException thrown if empty
     */
    public int smallest() {
        if (set.isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }
        int smallest = set.get(0);
        for (int num : set) {
            if (num < smallest) {
                smallest = num;
            }
        }
        return smallest;
    }

    // Adds an item to the set or does nothing if already there.
    /**
     * Adds an element to the set.
     * @param item the integer to add
     */
    public void add(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

    // Removes an item from the set or does nothing if not there.
    /**
     * Removes an element from the set.
     * @param item the integer to remove
     */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    // Set union: Union of this set and another set
    /**
     * Performs a union operation with another set.
     * @param intSetb the other set
     */
    public void union(IntegerSet intSetb) {
        for (int item : intSetb.set) {
            this.add(item);  // Add all items from intSetb to this set
        }
    }

    // Set intersection: Common elements in this set and another set
    /**
     * Performs an intersection operation with another set.
     * @param intSetb the other set
     */
    public void intersect(IntegerSet intSetb) {
        List<Integer> intersection = new ArrayList<>();
        for (int item : set) {
            if (intSetb.contains(item)) {
                intersection.add(item);
            }
        }
        set = intersection;  // Update the current set to be the intersection
    }

    // Set difference: Elements in this set but not in the other
    /**
     * Computes the difference between two sets.
     * @param intSetb the other set
     */
    public void diff(IntegerSet intSetb) {
        List<Integer> difference = new ArrayList<>();
        for (int item : set) {
            if (!intSetb.contains(item)) {
                difference.add(item);
            }
        }
        set = difference;  // Update the current set to be the difference
    }

    // Set complement: Elements not in this set
    /**
     * Computes the complement of the set with respect to another set.
     * @param intSetb the universal set
     */
    public void complement(IntegerSet intSetb) {
        List<Integer> complement = new ArrayList<>();
        for (int item : intSetb.set) {
            if (!set.contains(item)) {
                complement.add(item);
            }
        }
        set = complement;  // Update the current set to be the complement
    }

    // Returns true if the set is empty, false otherwise.
    /**
     * Checks if the set is empty.
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    // Return String representation of your set
    /**
     * Returns a string representation of the set.
     * @return string representation of the set
     */
    @Override
    public String toString() {
        return set.toString();
    }
}