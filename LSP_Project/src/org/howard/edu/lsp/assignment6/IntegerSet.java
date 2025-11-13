package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.List;

/**
 * A mathematical set of integers implemented using an ArrayList.
 * No duplicates are permitted. Supports standard set operations.
 */
public class IntegerSet {

    private List<Integer> set = new ArrayList<Integer>();

    /**
     * Clears the set.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the number of elements in the set.
     * @return number of elements
     */
    public int length() {
        return set.size();
    }

    /**
     * Compares this set with another object for equality.
     * Two sets are equal if they contain the same elements in ANY order.
     * @param o object to compare
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        IntegerSet other = (IntegerSet) o;

        if (this.length() != other.length())
            return false;

        // Order does not matter; compare membership
        return this.set.containsAll(other.set) && other.set.containsAll(this.set);
    }

    /**
     * Checks if the set contains a value.
     * @param value value to check
     * @return true if contained, false otherwise
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Returns the largest element.
     * Throws IllegalStateException if the set is empty.
     */
    public int largest() {
        if (set.isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }

        int max = set.get(0);
        for (int num : set) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    /**
     * Returns the smallest element.
     * Throws IllegalStateException if the set is empty.
     */
    public int smallest() {
        if (set.isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }

        int min = set.get(0);
        for (int num : set) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }

    /**
     * Adds an integer to the set if not already present.
     * @param item value to add
     */
    public void add(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes an element from the set if present.
     * @param item value to remove
     */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /**
     * Set union: modifies THIS set to contain all elements
     * from both sets (no duplicates allowed).
     * @param other other set
     */
    public void union(IntegerSet other) {
        for (int value : other.set) {
            this.add(value);
        }
    }

    /**
     * Set intersection: modifies THIS set to keep only the
     * elements that appear in both sets.
     * @param other other set
     */
    public void intersect(IntegerSet other) {
        List<Integer> newSet = new ArrayList<>();

        for (int value : this.set) {
            if (other.contains(value)) {
                newSet.add(value);
            }
        }

        // modify this.set without replacing list object
        set.clear();
        set.addAll(newSet);
    }

    /**
     * Set difference (this \ other): removes all elements from THIS set
     * that also exist in the other set.
     * @param other other set
     */
    public void diff(IntegerSet other) {
        List<Integer> newSet = new ArrayList<>();

        for (int value : this.set) {
            if (!other.contains(value)) {
                newSet.add(value);
            }
        }

        set.clear();
        set.addAll(newSet);
    }

    /**
     * Set complement: modifies THIS set to become (other \ this),
     * i.e., elements in other that are NOT in this.
     * @param other universal set
     */
    public void complement(IntegerSet other) {
        List<Integer> newSet = new ArrayList<>();

        for (int value : other.set) {
            if (!this.set.contains(value)) {
                newSet.add(value);
            }
        }

        set.clear();
        set.addAll(newSet);
    }

    /**
     * Checks if the set is empty.
     * @return true if empty
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns a formatted string showing the set.
     * @return "[a, b, c]"
     */
    @Override
    public String toString() {
        return set.toString();
    }
}