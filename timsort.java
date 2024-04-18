/**
 * Timsort
 */
public class Timsort {

    private static int minrun;

    public static void timsort (Integer[] array) {

        // Compute minrun
        if (array.length < 64) {
            minrun = array.length;
        } else if ((Math.log(array.length) / Math.log(2)) % 1 == 0) {
            // minrun;
        }

        int leftRunLength;
        int rightRunLength;

        // Iterate over the entire array left to right
        for (int i = 0; i < array.length; i += leftRunLength + rightRunLength) {
            // Alternately identify the next run
            leftRunLength = CountRun(array, i);
            // index 0 to runlength is fist run

            rightRunLength = CountRun(array, leftRunLength);
            // index runlength to next run is secon run

            // merge first run with second run into the previous run *intelligently*
            MergeRuns(array, i, leftRunLength, rightRunLength);
        }

    }

    public static int CountRun(Integer[] array, int start) {
        int elementsInNextRun = 0;

        if (start < array.length) {
            do {
                elementsInNextRun++;
            } while (array[start] <= array[++start] && start < array.length);

            if (elementsInNextRun < minrun) {
                elementsInNextRun = minrun;
                BinaryInsertionSort(array, start, elementsInNextRun);
            }
        }

        return elementsInNextRun;
    }

    /**
     * Merges two runs
     * @param array
     * @param start
     * @param leftRunLength
     * @param rightRunLength
     */
    public static void MergeRuns(Integer[] array, int start, int leftRunLength, int rightRunLength) {

    }

    /**
     * Sorts an Integer array in non-decreasing order.
     * @param array Array to sort.
     * @param start index to start sort
     * @param runLength length of run to sort
     */
    public static void BinaryInsertionSort(Integer[] array, int start, int runLength) {

        // Loop through the array and sort each value starting at index 1.
        for (int indexOfValueToSort = 1; indexOfValueToSort < runLength; indexOfValueToSort++) {
            // Value to sort.
            int valueToSort = array[indexOfValueToSort];

            // Find Location to insert
            int insertIndex = BinarySearchInsert(array, valueToSort, 0, indexOfValueToSort - 1);

            // Move everything over one location
            for (int i = indexOfValueToSort; i > insertIndex; i--) {
                array[i] = array[i - 1];
            }

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