/**
 * Timsort
 */
public class Timsort {

    private static int minrun;

    public static void timsort (Integer[] array) {
        if (array.length < 64) {
            minrun = array.length;
        } else {
            minrun = 64;
        }

    }

    /**
     * Sorts an Integer array in non-decreasing order.
     * @param array Array to sort.
     */
    public static void BinaryInsertionSort(Integer[] array) {

        // Loop through the array and sort each value starting at index 1.
        for (int indexOfValueToSort = 1; indexOfValueToSort < array.length; indexOfValueToSort++) {
            // Value to sort.
            int valueToSort = array[indexOfValueToSort];

            // Find Location to insert
            int insertIndex = BinarySearchInsert(array, valueToSort, 0, indexOfValueToSort) + 1;

            // // Move everything over one location
            // for (j = i; j > 0 && arr[j - 1] > temp; j--) {
            //     array[j] = array[j - 1];
            // }

            // Insert the value at the insert index.
            array[insertIndex] = valueToSort;
        }
    }

    /**
     * Searches a sorted Integer array the index that a value should be inserted at.
     * @param array Array to search.
     * @param item Integer to search for.
     * @param low Lowest index of search.
     * @param high Highest index of search.
     * @return Returns the index that the Integer should be inserted into
     */
    public static int BinarySearchInsert(Integer[] array, Integer item, int low, int high) {
        // Find the middle of the array.
        int mid = low + (high - low) / 2;

        // If search area is only one integer
        if (low >= high) {
            // Check if the item is less than that integer
            if (item < array[mid]) {
                // If it is, return the index of that integer
                return mid;
            // Else the item is greater than or equal to that integer
            } else {
                // Retrun the index after the integer
                return mid + 1;
            }
        // Else if the search area is greater than one integer
        // Check if the item should be before or after the value at the middle index
        } else if (item < array[mid]) {
            // Search the left side of the array.
            return BinarySearchInsert(array, item, low, mid - 1);
        // Else item is on the right side of the array
        } else {
            // Search the right side of the array.
            return BinarySearchInsert(array, item, mid + 1, high);
        }
    }
}