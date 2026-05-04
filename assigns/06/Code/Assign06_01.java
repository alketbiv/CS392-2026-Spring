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

} // end of [public class Assign06_01{...}]
