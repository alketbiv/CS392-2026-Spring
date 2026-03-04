import MyLibrary.FnList.*;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public class Assign05_01 {

    public static
    <T extends Comparable<T>>
    FnList<T> insertSort(FnList<T> xs) {
        return insertSort(xs, (x1, x2) -> x1.compareTo(x2));
    }
//
    public static<T> FnList<T>
    insertSort(FnList<T> xs, ToIntBiFunction<T,T> cmp) {

        // Loop-based, stable insertion sort. No recursion.

        FnList<T> sorted = FnListSUtil.nil();
        FnList<T> cur = xs;

        while (cur.consq()) {
            T x = cur.hd();

            // Build prefix (elements <= x) in reverse order
            FnList<T> prefixRev = FnListSUtil.nil();
            FnList<T> scan = sorted;

            // STABLE: skip elements <= x so x goes AFTER equals
            while (scan.consq() && cmp.applyAsInt(x, scan.hd()) >= 0) {
                prefixRev = FnListSUtil.cons(scan.hd(), prefixRev);
                scan = scan.tl();
            }

            // Insert x before first element > x
            FnList<T> result = FnListSUtil.cons(x, scan);

            // Restore prefix
            while (prefixRev.consq()) {
                result = FnListSUtil.cons(prefixRev.hd(), result);
                prefixRev = prefixRev.tl();
            }

            sorted = result;
            cur = cur.tl();
        }

        return sorted;
    }

    public static void main(String[] args) {

        final int N = 10000;

        // Build: 1,0,3,2,5,4,...,999999,999998
        FnList<Integer> xs = FnListSUtil.nil();
        for (int i = N - 2; i >= 0; i -= 2) {
            xs = FnListSUtil.cons(i, xs);
            xs = FnListSUtil.cons(i + 1, xs);
        }

        long t0 = System.currentTimeMillis();
        FnList<Integer> ys = insertSort(xs);
        long t1 = System.currentTimeMillis();

        // Correctness check (hidden tests will also do this)
        boolean ok = true;
        if (ys.consq()) {
            int prev = ys.hd();
            FnList<Integer> p = ys.tl();
            while (p.consq()) {
                int v = p.hd();
                if (v < prev) { ok = false; break; }
                prev = v;
                p = p.tl();
            }
        }

        System.out.println("sorted_ok=" + ok);
        System.out.println("ms=" + (t1 - t0));
    }

} // end of [public class Assign05_01{...}]
