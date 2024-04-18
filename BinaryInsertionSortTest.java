import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.validator.PublicClassValidator;

public class BinaryInsertionSortTest {

    private static Integer[] SINGLEARRAY = new Integer[]{5};
    private static Integer[] SORTEDARRAY = new Integer[]{5, 6};
    private static Integer[] UNSORTEDARRAY = new Integer[]{6, 5};

    @Test
    public void BinaryInsertionSort_ArrayLengthOne_ArrayDoesntChange() {
        // Arrange
        Integer[] Array = SINGLEARRAY;

        // Act
        Timsort.BinaryInsertionSort(Array, 0, Array.length);

        // Assert
        assertTrue(IsSorted(Array));
    }

    @Test
    public void BinaryInsertionSort_SortedArray_ArrayDoesntChange() {
        // Arrange
        Integer[] Array = SORTEDARRAY;

        // Act
        Timsort.BinaryInsertionSort(Array, 0, Array.length);

        // Assert
        assertTrue(IsSorted(Array));
    }

    @Test
    public void BinaryInsertionSort_UnsortedArray_ArrayIsSorted() {
        // Arrange
        Integer[] Array = UNSORTEDARRAY;

        // Act
        Timsort.BinaryInsertionSort(Array, 0, Array.length);

        // Assert
        assertTrue(IsSorted(Array));
    }

    public boolean IsSorted(Integer[] array) {
        if (array.length > 1) {
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    return false;
                }
            }
        }
        return true;
    }
}
