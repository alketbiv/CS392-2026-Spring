/*
 *  Array-based Quicksort
 */
import java.util.Random;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public class Assign06_01 {

    private static final Random rand = new Random();

    public static <T> void arrayQuickSort(T[] A, ToIntBiFunction<T,T> cmp) {
        if (A == null || A.length <= 1) return;
        quickSort(A, 0, A.length - 1, cmp);
    }

    private static <T> void quickSort(T[] A, int lo, int hi, ToIntBiFunction<T,T> cmp) {
        if (lo >= hi) return;

        int pivotIndex = lo + rand.nextInt(hi - lo + 1);
        swap(A, lo, pivotIndex);
        T pivot = A[lo];

        int lt = lo;
        int i = lo + 1;
        int gt = hi;

        while (i <= gt) {
            int c = cmp.applyAsInt(A[i], pivot);
            if (c < 0) {
                swap(A, lt, i);
                lt++;
                i++;
            } else if (c > 0) {
                swap(A, i, gt);
                gt--;
            } else {
                i++;
            }
        }

        quickSort(A, lo, lt - 1, cmp);
        quickSort(A, gt + 1, hi, cmp);
    }

    private static <T> void swap(T[] A, int i, int j) {
        T temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
public static void main(String[] args) {
    ToIntBiFunction<Integer,Integer> cmp = (x, y) -> Integer.compare(x, y);

    Integer[] A1 = {5, 3, 8, 1, 2, 7, 4, 6};
    arrayQuickSort(A1, cmp);
    System.out.println(isSorted(A1, cmp)); // should print true

    Integer[] A2 = {1, 2, 3, 4, 5};
    arrayQuickSort(A2, cmp);
    System.out.println(isSorted(A2, cmp)); // should print true

    Integer[] A3 = {3, 5, 3, 3, 2, 8, 5, 2, 1};
    arrayQuickSort(A3, cmp);
    System.out.println(isSorted(A3, cmp)); // should print true

    Integer[] A4 = new Integer[1000000];
    for (int i = 0; i < A4.length; i++) A4[i] = 0;
    arrayQuickSort(A4, cmp);
    System.out.println(isSorted(A4, cmp)); // should print true

    Integer[] A5 = {};
    arrayQuickSort(A5, cmp);
    System.out.println(isSorted(A5, cmp)); // should print true

    Integer[] A6 = {42};
    arrayQuickSort(A6, cmp);
    System.out.println(isSorted(A6, cmp)); // should print true
}

public static <T> boolean isSorted(T[] A, ToIntBiFunction<T,T> cmp) {
    for (int i = 1; i < A.length; i++) {
        if (cmp.applyAsInt(A[i - 1], A[i]) > 0) {
            return false;
        }
    }
    return true;
}
} // end of [public class Assign06_01{...}]
