package timsort;

import java.util.Arrays;

public class TimsortTest {
    Integer[] empty = new Integer[0];
    Integer[] lessThan64 = new Integer[63];
    Integer[] greaterThan64 = new Integer[128];
    Integer[] big = new Integer[2112];


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

        Integer[] correctArray = Arrays.copyOf(array, array.length);
        Arrays.sort(correctArray);

        // Act
        Timsort.timsort(array);

        // Assert
        assertEquals(printArray(correctArray), printArray(array));
        assertTrue(IsSorted(array));
    }

    @Test
    public void timsort_BigArray_ArrayIsSorted() {
        // Arrange
        Integer[] array = big;
        populateArray(array);

        // Act
        Timsort.timsort(array);
        // assertEquals("", printArray(array));

        // Assert
        assertTrue(IsSorted(array));
    }

    @Test
    public void ComputeMinRun() {
        // Arrange
        int[] length = {32, 63, 64, 65, 127, 128, 129, 255, 256, 257};
        int[] minrun = new int[length.length];

        // Act
        for (int i = 0; i < length.length; i++) {
            minrun[i] = Timsort.ComputeMinrun(length[i]);
        }

        // Assert
        assertEquals(32, minrun[0]);
        assertEquals(63, minrun[1]);
        assertEquals(32, minrun[2]);
        assertEquals(33, minrun[3]);
        assertEquals(64, minrun[4]);
        assertEquals(32, minrun[5]);
        assertEquals(33, minrun[6]);
        assertEquals(64, minrun[7]);
        assertEquals(32, minrun[8]);
        assertEquals(33, minrun[9]);

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

    public String printArray(Integer[] array) {
        String result = "";
        for (int i = 0; i < array.length; i++) {
            result += array[i] + ", ";
        }
        return result;
    }
}
