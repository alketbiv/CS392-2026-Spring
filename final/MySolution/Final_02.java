/*
// HX: 50 points for Final_02
*/

import MyLibrary.FnList.*;
import MyLibrary.FnTuple.*;
import MyLibrary.LnStrm.*;

public class Final_02 {

    private static int compareWords(FnList<Character> w1, FnList<Character> w2) {
        FnList<Character> c1 = w1;
        FnList<Character> c2 = w2;
        while (c1.consq() && c2.consq()) {
            char ch1 = c1.hd();
            char ch2 = c2.hd();
            if (ch1 < ch2) return -1;
            if (ch1 > ch2) return 1;
            c1 = c1.tl(); c2 = c2.tl();
        }
        if (c1.nilq() && c2.nilq()) return 0;
        if (c1.nilq()) return -1;
        return 1;
    }

    @SuppressWarnings("unchecked")
    private static FnList<Character>[] streamToArray(LnStrm<FnList<Character>> stream) {
        FnList<FnList<Character>> list = FnListSUtil.nil();
        LnStcn<FnList<Character>> node = stream.eval0();
        while (node != null && !node.nilq()) {
            list = FnListSUtil.cons(node.hd(), list);
            node = node.tl().eval0();
        }
        list = FnListSUtil.reverse(list);
        int size = list.length();
        FnList<Character>[] arr = new FnList[size];
        int i = 0;
        while (list.consq()) {
            arr[i++] = list.hd();
            list = list.tl();
        }
        return arr;
    }

    @SuppressWarnings("unchecked")
    private static void quickSort(FnList<Character>[] A, int lo, int hi) {
        if (lo >= hi) return;
        FnList<Character> pivot = A[hi];
        int i = lo;
        for (int j = lo; j < hi; j++) {
            if (compareWords(A[j], pivot) <= 0) {
                FnList<Character> tmp = A[i]; A[i] = A[j]; A[j] = tmp;
                i++;
            }
        }
        FnList<Character> tmp = A[i]; A[i] = A[hi]; A[hi] = tmp;
        quickSort(A, lo, i - 1);
        quickSort(A, i + 1, hi);
    }

    private static FnList<FnTupl2<FnList<Character>, Integer>> countWords(FnList<Character>[] A) {
        if (A.length == 0) return FnListSUtil.nil();
        FnList<FnTupl2<FnList<Character>, Integer>> result = FnListSUtil.nil();
        FnList<Character> current = A[0];
        int count = 1;
        for (int i = 1; i < A.length; i++) {
            if (compareWords(current, A[i]) == 0) {
                count++;
            } else {
                result = FnListSUtil.cons(new FnTupl2<>(current, count), result);
                current = A[i];
                count = 1;
            }
        }
        result = FnListSUtil.cons(new FnTupl2<>(current, count), result);
        return result;
    }

    private static <T> FnList<T> mergeSort(FnList<T> xs, java.util.function.ToIntBiFunction<T,T> cmp) {
        if (xs.nilq() || xs.tl().nilq()) return xs;
        int len = xs.length();
        int mid = len / 2;
        FnList<T> left = FnListSUtil.nil();
        FnList<T> right = FnListSUtil.nil();
        FnList<T> tmp = xs;
        for (int i = 0; i < len; i++) {
            if (i < mid) left = FnListSUtil.cons(tmp.hd(), left);
            else right = FnListSUtil.cons(tmp.hd(), right);
            tmp = tmp.tl();
        }
        left = FnListSUtil.reverse(left);
        right = FnListSUtil.reverse(right);
        return merge(mergeSort(left, cmp), mergeSort(right, cmp), cmp);
    }

    private static <T> FnList<T> merge(FnList<T> xs, FnList<T> ys, java.util.function.ToIntBiFunction<T,T> cmp) {
        if (xs.nilq()) return ys;
        if (ys.nilq()) return xs;
        if (cmp.applyAsInt(xs.hd(), ys.hd()) <= 0)
            return FnListSUtil.cons(xs.hd(), merge(xs.tl(), ys, cmp));
        else
            return FnListSUtil.cons(ys.hd(), merge(xs, ys.tl(), cmp));
    }

    static FnList<FnTupl2<FnList<Character>, Integer>> pg2701_word$count$listize2() {
        LnStrm<FnList<Character>> wordStream = Final_01.pg2701_word$strmize();
        FnList<Character>[] A1 = streamToArray(wordStream);
        quickSort(A1, 0, A1.length - 1);
        FnList<FnTupl2<FnList<Character>, Integer>> L2 = countWords(A1);
        return mergeSort(L2, (p1, p2) -> {
            int n1 = p1.sub1, n2 = p2.sub1;
            if (n1 > n2) return -1;
            if (n1 < n2) return 1;
            return compareWords(p1.sub0, p2.sub0);
        });
    }

    public static void main(String[] args) {
        FnList<FnTupl2<FnList<Character>, Integer>> result = pg2701_word$count$listize2();
        int count = 0;
        while (result.consq() && count < 100) {
            FnTupl2<FnList<Character>, Integer> pair = result.hd();
            pair.sub0.foritm(ch -> System.out.print(ch));
            System.out.println(" " + pair.sub1);
            result = result.tl();
            count++;
        }
        return /*void*/;
    }
}