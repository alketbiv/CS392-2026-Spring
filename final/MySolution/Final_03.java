/*
// HX: 50 points for Final_03
*/

import MyLibrary.FnList.*;
import MyLibrary.FnTuple.*;
import MyLibrary.LnStrm.*;
import MyLibrary.MyMap00.*;

public class Final_03 {

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

    private static String wordToString(FnList<Character> word) {
        StringBuilder sb = new StringBuilder();
        word.foritm(ch -> sb.append(ch));
        return sb.toString();
    }

    private static FnList<Character> stringToWord(String s) {
        FnList<Character> result = FnListSUtil.nil();
        for (int i = s.length() - 1; i >= 0; i--) {
            result = FnListSUtil.cons(s.charAt(i), result);
        }
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

    static FnList<FnTupl2<FnList<Character>, Integer>> pg2701_word$count$listize3() {
        LnStrm<FnList<Character>> wordStream = Final_01.pg2701_word$strmize();
        Assign08_02<Integer> map = new Assign08_02<Integer>();
        LnStcn<FnList<Character>> node = wordStream.eval0();
        while (node != null && !node.nilq()) {
            String key = wordToString(node.hd());
            Integer count = map.search$opt(key);
            if (count == null) map.insert$opt(key, 1);
            else map.insert$opt(key, count + 1);
            node = node.tl().eval0();
        }
        FnList<FnTupl2<FnList<Character>, Integer>> WNS = FnListSUtil.nil();
        map.foritm((k, v) -> {
            // WNS is effectively final workaround not needed since we use array
        });
        final FnList<FnTupl2<FnList<Character>, Integer>>[] acc = new FnList[]{FnListSUtil.nil()};
        map.foritm((k, v) -> {
            acc[0] = FnListSUtil.cons(new FnTupl2<>(stringToWord(k), v), acc[0]);
        });
        WNS = acc[0];
        return mergeSort(WNS, (p1, p2) -> {
            int n1 = p1.sub1, n2 = p2.sub1;
            if (n1 > n2) return -1;
            if (n1 < n2) return 1;
            return compareWords(p1.sub0, p2.sub0);
        });
    }

    public static void main(String[] args) {
        FnList<FnTupl2<FnList<Character>, Integer>> result = pg2701_word$count$listize3();
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