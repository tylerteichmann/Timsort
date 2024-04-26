package timsort;
import java.util.Arrays;
import java.util.Stack;

/**
 * Timsort
 */
public class Timsort {

    /**
     * Sorts an array of Integers using the timsort algorithm.
     * @param array Array to be sorted.
     */
    public static void timsort (Integer[] array) {
        // Compute minrun
        int minrun = ComputeMinrun(array.length);

        // Cursor for tracking position in the array
        int cursor = 0;

        // Stack for tracking runs
        Stack<int[]> mergeState = new Stack<int[]>();

        // Iterate over the entire array left to right
        while (cursor < array.length) {
            // Alternately identify the next run
            int runLength = CountRun(array, cursor, minrun);

            // Create an array that will store the runs start and its length
            int[] run = {cursor, runLength};

            // Add the run to the stack
            mergeState.push(run);

            // check the stack and merge *intelligently*
            MergeCollapse(array, mergeState);

            // Move the cursor
            cursor += runLength;
        }
    }

    public static int ComputeMinrun(int N) {
        //https://stackoverflow.com/questions/47133993/how-to-calculate-the-minrun-length-for-timsort-in-python
        int minrun;

        // If the length is less than 63
        if (N < 64) {
            // Set the minrun equal to the length
            minrun = N;
        // Else
        } else {
            // Find the position of the six most significant bits
            int sixMostSignificantBits = (int)(Math.log((double)N)/Math.log(2.0)) - 5;
            // Set the min run equal to the int value of those six bits.
            // Move each bit over so that the last most significant bit is the first bit
            minrun = N >> sixMostSignificantBits;
            // Create an integer that has 1s in every bit after the six most significant
            int mask = (1 << sixMostSignificantBits) - 1;
            // If any of the bits after the six most significant are set.
            if ((N & mask) > 0) {
                // Add one
                minrun ++;
            }
        }

        return minrun;
    }

    public static int CountRun(Integer[] array, int start, int minrun) {

        int elementsInNextRun = 1;
        int index = start;

        if (start + minrun >= array.length) {
            elementsInNextRun = array.length - start;
            BinaryInsertionSort(array, start, elementsInNextRun);
        } else {
            while (array[index] <= array[++index]) {
                elementsInNextRun++;
                if (index == array.length){
                    return elementsInNextRun;
                }
            }

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
    public static void MergeCollapse(Integer[] array, Stack<int[]> mergeState) {
        if (mergeState.size() > 1) {
            // First item on the stack
            int[] B = mergeState.pop();
            // Second Item on the stack
            int[] A = mergeState.pop();

            // merge the two
            if (A[1] <= B[1]) {
                MergeLo(array, A[0], A[1], B[0], B[1]);
            } else {
                MergeHi(array, A[0], A[1], B[0], B[1]);
            }

            int[] newRun = {A[0], A[1] + B[1]};

            // Add back to the stack
            mergeState.push(newRun);
        }
    }

    public static void MergeLo(Integer[] array, int AStart, int ALength, int BStart, int BLength) {
        // Copy A to temp Array
        Integer[] tempArray = Arrays.copyOfRange(array, AStart, AStart + ALength);

        int insertPointer = AStart;
        int APointer = 0;
        int BPointer = BStart;


        while(
            insertPointer < BStart + BLength &&
            APointer < ALength &&
            BPointer < BStart + BLength
        ) {
            if (tempArray[APointer] > array[BPointer]) {
                array[insertPointer] = array[BPointer];
                BPointer++;
            } else {
                array[insertPointer] = tempArray[APointer];
                APointer++;
            }
            insertPointer++;
        }

        if (BPointer == BStart + BLength) {
            while (APointer < ALength) {
                array[insertPointer] = tempArray[APointer];
                insertPointer++;
                APointer++;
            }
        }
    }

    public static void MergeHi(Integer[] array, int AStart, int ALength, int BStart, int BLength) {
        // Copy B to temp Array
        Integer[] tempArray = Arrays.copyOfRange(array, BStart, BStart + BLength);

        int insertPointer = BStart + BLength - 1;
        int APointer = AStart + ALength - 1;
        int BPointer = BLength - 1;


        while(
            insertPointer >= AStart &&
            BPointer >= 0 &&
            APointer >= AStart
        ) {
            if (tempArray[BPointer] < array[APointer]) {
                array[insertPointer] = array[APointer];
                APointer--;
            } else {
                array[insertPointer] = tempArray[BPointer];
                BPointer--;
            }
            insertPointer--;
        }

        if (APointer < AStart) {
            while (BPointer >= 0) {
                array[insertPointer] = tempArray[BPointer];
                insertPointer--;
                BPointer--;
            }
        }
    }

    /**
     * Sorts an Integer array in non-decreasing order.
     * @param array Array to sort.
     * @param start index to start sort
     * @param runLength length of run to sort
     */
    public static void BinaryInsertionSort(Integer[] array, int start, int runLength) {

        // Loop through the array and sort each value starting at index 1.
        for (int indexOfValueToSort = start + 1; indexOfValueToSort < start + runLength; indexOfValueToSort++) {
            // Value to sort.
            int valueToSort = array[indexOfValueToSort];

            // Find Location to insert
            int insertIndex = BinarySearchInsert(array, valueToSort, start, indexOfValueToSort - 1);

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