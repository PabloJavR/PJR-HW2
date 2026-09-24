public class Main {
    public static void main(String[] args) {

        //Pablo Reyes
        // java
        // VCS

        // Part 1 & 2: 12 intergers
        int[] numbers = {5, 12, 7, 3, 9, 15, 2, 8, 10, 6, 4, 11};

        System.out.println("Original Array: ");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Part 3: Bubble Sort (copy the array so Merge Sort still gets the original order)
        int[] bArr = numbers.clone();
        bubbleSort(bArr);

        System.out.println("\nBubble Sort Result: ");
        for (int num : bArr) {
            System.out.print(num + " ");
        }
        System.out.println();

        /* Part 4: Bubble Sort Big O
         * Q1: The worst case is Big O of Bubble Sort is O(n^2)
         * Q2: The reason is that Bubble Sort has two nested loops. For example, for every element, it
         *     recompares against most of the other elements. That repeated nested comparing is what makes it n * n instead of just n.
         * Q3: With 10 elements, Bubble Sort does roughly 10^2 = so about 100 comparisons.
         *     That includes 1,000 elements. the work would be 10,000x, because
         *     O(n^2) means the work grows with the SQUARE of the input, not the same rate.
         */

        // Part 5: Merge Sort
        int[] mArr = numbers.clone();
        mergeSort(mArr, 0, mArr.length - 1);

        System.out.println("\nMerge Sort Result: ");
        for (int num : mArr) {
            System.out.print(num + " ");
        }
        System.out.println();

        /* Part 6: Compare Bubble Sort and Merge Sort
         * Q4: Big O of Merge Sort is O(n log n).
         * Q5: Merge Sort performs better on very large data. Bubble Sort's O(n^2) runtime
         *     explodes as n grows (see Part 4's math), while Merge Sort's O(n log n) grows
         *     much more slowly since splitting the array in half over and over (log n) is
         *     cheap compared to repeatedly rescanning the whole array.
         * Q6: Bubble Sort = O(n^2)
         *     Merge Sort  = O(n log n)
         */

        // Part 7 & 8: Linear Search on the original unsorted array
        System.out.println("\nLinear Search");
        linearSearchTest(numbers, 12);  // near the beginning
        linearSearchTest(numbers, 4);   // near the end
        linearSearchTest(numbers, 100); // does not exist

        // Part 9 & 10: Binary Search, needs a sorted array
        System.out.println("\nBinary Search");
        binarySearchTest(bArr, 2);   // near the beginning
        binarySearchTest(bArr, 15);  // near the end
        binarySearchTest(bArr, 100); // does not exist

        /* Part 11: Searching Questions
         * Q7: Linear Search is O(n), it just checks one spot at a time until it finds
         *     what it's looking for or runs clean out of array.
         * Q8: Binary Search is O(log n) since it keeps cutting the search area in half
         *     instead of checking every single value.
         * Q9: It needs sorted data because the whole method relies on comparing to the
         *     middle value to decide which half to toss out, and that logic falls apart
         *     the moment the numbers aren't actually in order.
         * Q10: If the data isn't sorted, Linear Search makes more sense, since sorting
         *      everything first just to use Binary Search ends up being more work than
         *      it's worth.
         * Q11: For a huge sorted array though, Binary Search is the better pick, since it
         *      barely slows down even as the array keeps getting bigger.
         */
    }

    // Bubble Sort method
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Merge Sort method: splits the array in half until each piece has 1 element, then merges them back sorted
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);      // sort left half
            mergeSort(arr, mid + 1, right); // sort right half
            merge(arr, left, mid, right);   // merge both halves
        }
    }

    // Helper for Merge Sort, combines two sorted halves into one sorted section
    public static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[mid + 1 + j];
        }

        // walk through both halves and place the smaller value back into arr
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // add whatever is left over from either side
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Linear Search method, checks each spot one at a time
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1; // not found
    }

    // Runs a Linear Search and prints the result
    public static void linearSearchTest(int[] arr, int target) {
        int result = linearSearch(arr, target);
        System.out.println("Target: " + target);
        if (result != -1) {
            System.out.println("Target found at index " + result);
        } else {
            System.out.println("Target not found.");
        }
    }

    // Binary Search method, array must already be sorted
    public static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (target > arr[mid]) {
                low = mid + 1;  // ignore left half
            } else {
                high = mid - 1; // ignore right half
            }
        }
        return -1; // not found
    }

    // Runs a Binary Search and prints the result
    public static void binarySearchTest(int[] arr, int target) {
        int result = binarySearch(arr, target);
        System.out.println("Target: " + target);
        if (result != -1) {
            System.out.println("Target found at index " + result);
        } else {
            System.out.println("Target not found.");
        }
    }
}