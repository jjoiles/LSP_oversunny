package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * Test class for the IntegerSet class.
 * <p>
 * This class contains unit tests validating the behavior of IntegerSet methods,
 * including add, remove, clear, union, intersect, diff, complement, largest, smallest,
 * contains, equals, isEmpty, and toString.  
 * Performance tests for large sets are also included.
 */
public class IntegerSetTest {

    /**
     * Tests that clear() removes all elements from the set.
     */
    @Test
    @DisplayName("Test case for clear")
    public void testClear() {
        IntegerSet set = new IntegerSet();
        set.add(10);
        set.clear();
        assertEquals(0, set.length(), "Set should be empty after clear.");
    }

    /**
     * Tests that length() returns the correct number of elements.
     */
    @Test
    @DisplayName("Test case for length")
    public void testLength() {
        IntegerSet set = new IntegerSet();
        set.add(10);
        set.add(20);
        assertEquals(2, set.length(), "Length should return the correct number of elements.");
    }

    /**
     * Tests equals() for sets with the same elements in different orders.
     */
    @Test
    @DisplayName("Test case for equals (sets with same elements in a different order)")
    public void testEquals() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();
        set1.add(1);
        set1.add(2);
        set2.add(2);
        set2.add(1);
        assertTrue(set1.equals(set2), "Sets with the same elements but in different order should be equal.");
    }

    /**
     * Tests equals() for sets of different sizes.
     */
    @Test
    @DisplayName("Test case for equals (sets of different lengths)")
    public void testEqualsDifferentLengthSets() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();
        set1.add(1);
        set1.add(2);
        set2.add(1);
        assertFalse(set1.equals(set2), "Sets of different lengths should not be equal.");
    }

    /**
     * Tests contains() for values that exist and do not exist.
     */
    @Test
    @DisplayName("Test case for contains")
    public void testContains() {
        IntegerSet set = new IntegerSet();
        set.add(10);
        assertTrue(set.contains(10), "Set should contain 10.");
        assertFalse(set.contains(20), "Set should not contain 20.");
    }

    /**
     * Tests largest() with a non-empty set.
     */
    @Test
    @DisplayName("Test case for largest")
    public void testLargest() {
        IntegerSet set = new IntegerSet();
        set.add(10);
        set.add(20);
        assertEquals(20, set.largest(), "Largest element should be 20.");
    }

    /**
     * Tests that largest() throws an exception when called on an empty set.
     */
    @Test
    @DisplayName("Test case for largest with an empty set (exception)")
    public void testLargestEmpty() {
        IntegerSet set = new IntegerSet();
        assertThrows(IllegalStateException.class, set::largest, "Should throw exception when largest is called on empty set.");
    }

    /**
     * Tests smallest() with a non-empty set.
     */
    @Test
    @DisplayName("Test case for smallest")
    public void testSmallest() {
        IntegerSet set = new IntegerSet();
        set.add(10);
        set.add(20);
        assertEquals(10, set.smallest(), "Smallest element should be 10.");
    }

    /**
     * Tests that smallest() throws an exception when called on an empty set.
     */
    @Test
    @DisplayName("Test case for smallest with an empty set (exception)")
    public void testSmallestEmpty() {
        IntegerSet set = new IntegerSet();
        assertThrows(IllegalStateException.class, set::smallest, "Should throw exception when smallest is called on empty set.");
    }

    /**
     * Tests add() to ensure duplicate values are not added.
     */
    @Test
    @DisplayName("Test case for add with duplicate values")
    public void testAddDuplicates() {
        IntegerSet set = new IntegerSet();
        set.add(10);
        set.add(10);
        assertEquals(1, set.length(), "Set should contain only one 10.");
    }

    /**
     * Tests add() for adding multiple valid values.
     */
    @Test
    @DisplayName("Test case for add")
    public void testAdd() {
        IntegerSet set = new IntegerSet();
        set.add(10);
        set.add(20);
        assertTrue(set.contains(10), "Set should contain 10.");
        assertTrue(set.contains(20), "Set should contain 20.");
    }

    /**
     * Performance test for adding many elements.
     */
    @Test
    @DisplayName("Test performance with large set")
    public void testLargeSetAdd() {
        IntegerSet set = new IntegerSet();
        for (int i = 0; i < 2000; i++) {
            set.add(i);
        }
        assertEquals(2000, set.length(), "Set should have 2000 elements.");
    }

    /**
     * Tests remove() for removing an existing element.
     */
    @Test
    @DisplayName("Test case for remove")
    public void testRemove() {
        IntegerSet set = new IntegerSet();
        set.add(10);
        set.remove(10);
        assertFalse(set.contains(10), "Set should not contain 10 after removal.");
    }

    /**
     * Tests remove() when the element does not exist in the set.
     */
    @Test
    @DisplayName("Test case for remove (item not in set)")
    public void testRemoveItemNotInSet() {
        IntegerSet set = new IntegerSet();
        set.add(10);
        set.remove(20);
        assertEquals(1, set.length(), "Removing an item not in the set should not change the set.");
    }

    /**
     * Tests union() to ensure all unique elements from both sets are included.
     */
    @Test
    @DisplayName("Test case for union")
    public void testUnion() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();
        set1.add(1);
        set2.add(2);
        set1.union(set2);
        assertEquals(2, set1.length(), "Union of sets should contain both elements.");
    }

    /**
     * Performance test for union() with large sets.
     */
    @Test
    @DisplayName("Test performance with large set union")
    public void testLargeSetUnion() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();
        for (int i = 0; i < 1000; i++) {
            set1.add(i);
            set2.add(i + 1000);
        }
        set1.union(set2);
        assertEquals(2000, set1.length(), "Union of large sets should contain all elements.");
    }

    /**
     * Tests intersect() to ensure only common elements remain.
     */
    @Test
    @DisplayName("Test case for intersect")
    public void testIntersect() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();
        set1.add(1);
        set1.add(2);
        set2.add(2);
        set1.intersect(set2);
        assertEquals(1, set1.length(), "Intersection should contain the common element.");
    }

    /**
     * Tests diff() to ensure elements only in set1 remain.
     */
    @Test
    @DisplayName("Test case for diff")
    public void testDiff() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();
        set1.add(1);
        set1.add(2);
        set2.add(2);
        set1.diff(set2);
        assertEquals(1, set1.length(), "Difference should contain the elements only in set1.");
    }

    /**
     * Tests complement() to ensure elements not in set1 are kept from set2.
     */
    @Test
    @DisplayName("Test case for complement")
    public void testComplement() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();
        set1.add(1);
        set2.add(1);
        set2.add(2);
        set1.complement(set2);
        assertEquals(1, set1.length(), "Complement should contain elements not in set1.");
    }

    /**
     * Tests isEmpty() for both empty and non-empty sets.
     */
    @Test
    @DisplayName("Test case for isEmpty")
    public void testIsEmpty() {
        IntegerSet set = new IntegerSet();
        assertTrue(set.isEmpty(), "Set should be empty.");
        set.add(10);
        assertFalse(set.isEmpty(), "Set should not be empty after adding an element.");
    }

    /**
     * Tests toString() for an empty set.
     */
    @Test
    @DisplayName("Test case for toString (with an empty set)")
    public void testToStringEmptySet() {
        IntegerSet set = new IntegerSet();
        assertEquals("[]", set.toString(), "toString should return empty list representation for an empty set.");
    }

    /**
     * Tests toString() for a set containing multiple elements.
     */
    @Test
    @DisplayName("Test case for toString (with elements)")
    public void testToStringWithElements() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(2);
        assertEquals("[1, 2]", set.toString(), "toString should return correct string representation of the set.");
    }
}