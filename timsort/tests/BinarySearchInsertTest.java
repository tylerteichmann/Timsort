
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import timsort.Timsort;

public class BinarySearchInsertTest {
    
    private static final Integer[] SINGLEARRAY = new Integer[]{5};
    private static final Integer[] DOUBLEARRAY = new Integer[]{5, 7};
    private static final Integer LESSTHAN = 3;
    private static final Integer GREATERTHAN = 9;
    private static final Integer BETWEEN = 6;

    @Test
    public void BinarySearchInsert_ValueIsLessThan_InsertBeforeReturnZero() {
        // Arrange

        // Act
        int indexToInsert = Timsort.BinarySearchInsert(SINGLEARRAY, LESSTHAN, 0, SINGLEARRAY.length - 1);

        // Assert
        assertEquals(0, indexToInsert);
    }

    @Test
    public void BinarySearchInsert_ValueIsGreaterThanOrEqual_InsertAfterReturnOne() {
        // Arrange

        // Act
        int indexToInsert = Timsort.BinarySearchInsert(SINGLEARRAY, GREATERTHAN, 0, SINGLEARRAY.length - 1);

        // Assert
        assertEquals(1, indexToInsert);
    }

    @Test
    public void BinarySearchInsert_ValueIsLessThanEverything_InsertBeforeReturnZero() {
        // Arrange

        // Act
        int indexToInsert = Timsort.BinarySearchInsert(DOUBLEARRAY, LESSTHAN, 0, DOUBLEARRAY.length - 1);

        // Assert
        assertEquals(0, indexToInsert);
    }

    @Test
    public void BinarySearchInsert_ValueIsGreaterThanOrEqualToEverything_InsertAfterReturnArrayLength() {
        // Arrange

        // Act
        int indexToInsert = Timsort.BinarySearchInsert(DOUBLEARRAY, GREATERTHAN, 0, DOUBLEARRAY.length - 1);

        // Assert
        assertEquals(DOUBLEARRAY.length, indexToInsert);
    }

    @Test
    public void BinarySearchInsert_ValueIsBetween_InsertAfterReturnOne() {
        // Arrange

        // Act
        int indexToInsert = Timsort.BinarySearchInsert(DOUBLEARRAY, BETWEEN, 0, DOUBLEARRAY.length - 1);

        // Assert
        assertEquals(1, indexToInsert);
    }

}
