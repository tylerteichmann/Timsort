
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class TimsortTest {
    Integer[] empty = new Integer[0];
    Integer[] lessThan64 = new Integer[63];
    Integer[] greaterThan64 = new Integer[128];






    // Timsort Tests
    @Test
    public void timsort_EmptyArray_ArrayIsSorted() {
        // Arrange
        Integer[] array = empty;
        populateArray(array);

        // Act
        Timsort.timsort(array);

        // Assert
        assertTrue(IsSorted(array));
    }

    @Test
    public void timsort_ArrayLengthIsLessThan64_ArrayIsSorted() {
        // Arrange
        Integer[] array = lessThan64;
        populateArray(array);

        // Act
        Timsort.timsort(array);

        // Assert
        assertTrue(IsSorted(array));
    }

    @Test
    public void timsort_ArrayLengthGreaterThan64_ArrayIsSorted() {
        // Arrange
        Integer[] array = greaterThan64;
        populateArray(array);

        // Act
        Timsort.timsort(array);

        // Assert
        assertTrue(IsSorted(array));
    }








    // Helpers
    public void populateArray(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 486);
        }
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
