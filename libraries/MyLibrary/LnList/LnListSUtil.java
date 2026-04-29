package MyLibrary.LnList;

import MyLibrary.FnList.*;
import MyLibrary.FnA1sz.*;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public class LnListSUtil {
//
    public static<T>
        LnList<T> nil() {
        return new LnList<T>();
    }
    public static<T>
        LnList<T>
        cons(T x0, LnList<T> xs) {
        return new LnList<T>(x0, xs);
    }
//
    public static<T>
        boolean nilq1(LnList<T> xs) {
        return xs.nilq1();
    }
    public static<T>
        boolean consq1(LnList<T> xs) {
        return xs.consq1();
    }
//
    public static<T>
        LnList<T> reverse0(LnList<T> xs) {
        return xs.reverse0();
    }
//
    // Insertion sort on LnList (iterative, no recursion)
    public static<T>
        LnList<T>
        insertSort(LnList<T> xs, ToIntBiFunction<T,T> cmp) {
        LnList<T> sorted = nil();
        while (xs.consq1()) {
            T x0 = xs.hd1();
            xs = xs.tl1();
            sorted = insertSort_insert(sorted, x0, cmp);
        }
        return sorted;
    }
    public static
        <T extends Comparable<T>>
        LnList<T> insertSort(LnList<T> xs) {
        return insertSort(xs, (x1, x2) -> x1.compareTo(x2));
    }
    private static<T>
        LnList<T>
        insertSort_insert(LnList<T> xs, T x0, ToIntBiFunction<T,T> cmp) {
        LnList<T> rev = nil();
        while (xs.consq1() && cmp.applyAsInt(xs.hd1(), x0) <= 0) {
            rev = cons(xs.hd1(), rev);
            xs = xs.tl1();
        }
        xs = cons(x0, xs);
        while (rev.consq1()) {
            xs = cons(rev.hd1(), xs);
            rev = rev.tl1();
        }
        return xs;
    }
//
    // Merge sort on LnList
    public static
        <T extends Comparable<T>>
        LnList<T> mergeSort(LnList<T> xs) {
        return mergeSort(xs, (x1, x2) -> x1.compareTo(x2));
    }
    public static<T>
        LnList<T>
        mergeSort(LnList<T> xs, ToIntBiFunction<T,T> cmp) {
        int n = xs.length1();
        if (n <= 1) return xs;
        // split
        LnList<T> left = nil();
        LnList<T> right = xs;
        for (int i = 0; i < n/2; i++) {
            left = cons(right.hd1(), left);
            right = right.tl1();
        }
        left = left.reverse0();
        return merge(mergeSort(left, cmp), mergeSort(right, cmp), cmp);
    }
    private static<T>
        LnList<T>
        merge(LnList<T> xs, LnList<T> ys, ToIntBiFunction<T,T> cmp) {
        LnList<T> res = nil();
        while (xs.consq1() && ys.consq1()) {
            if (cmp.applyAsInt(xs.hd1(), ys.hd1()) <= 0) {
                res = cons(xs.hd1(), res);
                xs = xs.tl1();
            } else {
                res = cons(ys.hd1(), res);
                ys = ys.tl1();
            }
        }
        while (xs.consq1()) { res = cons(xs.hd1(), res); xs = xs.tl1(); }
        while (ys.consq1()) { res = cons(ys.hd1(), res); ys = ys.tl1(); }
        return res.reverse0();
    }
//
} // end of [public class LnListSUtil{...}]
