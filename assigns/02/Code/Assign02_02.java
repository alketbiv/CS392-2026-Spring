import java.util.Arrays;

public class Assign02_02 {
    /*
      HX-2025-02-13: 10 points
      Recursion is a fundamental concept in programming.
      However, the support for recursion in Java is very limited.
      Nontheless, we will be making extensive use of recursion in
      this class.
     */

    /*
    // This is a so-called iterative implementation:
    public static <T extends Comparable<T> > int indexOf(T[] a, T key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            final int mid = lo + (hi - lo) / 2;
            final int sign = key.compareTo(a[mid]);
            if      (sign < 0) hi = mid - 1;
            else if (sign > 0) lo = mid + 1;
            else return mid;
        }
        return -1;
    }
    */

    public static <T extends Comparable<T> > int indexOf(T[] a, T key) {
        return indexOfRecursive(a, key, 0, a.length - 1);
    }

    private static <T extends Comparable<T> > int indexOfRecursive(T[] a, T key, int lo, int hi) {
        if (lo > hi) {
            return -1;
        }

        final int mid = lo + (hi - lo) / 2;
        final int sign = key.compareTo(a[mid]);

        if (sign < 0) {
            return indexOfRecursive(a, key, lo, mid - 1);
        } else if (sign > 0) {
            return indexOfRecursive(a, key, mid + 1, hi);
        } else {
            return mid;
        }
    }

    public static void main(String[] argv) {
        // Please write some testing code for your implementation of 'indexOf'

        Integer[] arr = {1, 3, 5, 7, 9, 11};

        System.out.println(indexOf(arr, 7));   // Expected: 3
        System.out.println(indexOf(arr, 2));   // Expected: -1

        String[] words = {"apple", "banana", "cherry", "date"};

        System.out.println(indexOf(words, "cherry")); // Expected: 2
        System.out.println(indexOf(words, "orange")); // Expected: -1
    }
}

