import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.validator.PublicClassValidator;

public class BinaryInsertionSortTest {

    private static Integer[] SINGLEARRAY = new Integer[]{5};
    private static Integer[] SORTEDARRAY = new Integer[]{5, 6};
    private static Integer[] UNSORTEDSINGLEARRAY = new Integer[]{6, 5};

    @Test
    public void BinaryInsertionSort_ArrayLengthOne_ArrayDoesntChange() {
        // Arrange

        // Act
        Timsort.BinaryInsertionSort(SINGLEARRAY);

        // Assert
        assertTrue(IsSorted(SINGLEARRAY));
    }

    @Test
    public void BinaryInsertionSort_SortedArray_ArrayDoesntChange() {
        // Arrange

        // Act
        Timsort.BinaryInsertionSort(SORTEDARRAY);

        // Assert
        assertTrue(IsSorted(SORTEDARRAY));
    }

    @Test
    public void BinaryInsertionSort_UnsortedArray_ArrayIsSorted() {
        // Arrange

        // Act
        Timsort.BinaryInsertionSort(UNSORTEDSINGLEARRAY);

        // Assert
        assertTrue(IsSorted(UNSORTEDSINGLEARRAY));
    }

    public boolean IsSorted(Integer[] array) {
        if (array.length > 1) {
            for (int i = 1; i < array.length; i++) {
                if (i - 1 > i) {
                    return false;
                }
            }
        }

        return true;
    }
}
