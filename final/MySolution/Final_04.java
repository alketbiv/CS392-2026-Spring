/*
// HX: 50 points for Final_04
*/

import MyLibrary.FnList.*;
import MyLibrary.FnTuple.*;
import MyLibrary.LnStrm.*;

public class Final_04 {

    static class RBSTMap {
        Node root = null;

        class Node {
            FnList<Character> key;
            int value;
            int size;
            Node parent;
            Node lchild;
            Node rchild;
        }

        private int compareKeys(FnList<Character> k1, FnList<Character> k2) {
            FnList<Character> c1 = k1, c2 = k2;
            while (c1.consq() && c2.consq()) {
                char ch1 = c1.hd(), ch2 = c2.hd();
                if (ch1 < ch2) return -1;
                if (ch1 > ch2) return 1;
                c1 = c1.tl(); c2 = c2.tl();
            }
            if (c1.nilq() && c2.nilq()) return 0;
            if (c1.nilq()) return -1;
            return 1;
        }

        public Integer get(FnList<Character> key) {
            Node curr = root;
            while (curr != null) {
                int cmp = compareKeys(key, curr.key);
                if (cmp == 0) return curr.value;
                else if (cmp < 0) curr = curr.lchild;
                else curr = curr.rchild;
            }
            return null;
        }

        public void put(FnList<Character> key, int value) {
            Node parent = null, curr = root;
            while (curr != null) {
                int cmp = compareKeys(key, curr.key);
                if (cmp == 0) { curr.value = value; return; }
                parent = curr;
                if (cmp < 0) curr = curr.lchild;
                else curr = curr.rchild;
            }
            Node node = new Node();
            node.key = key; node.value = value;
            node.size = 1; node.parent = parent;
            if (parent == null) root = node;
            else if (compareKeys(key, parent.key) < 0) parent.lchild = node;
            else parent.rchild = node;
        }

        public FnList<FnTupl2<FnList<Character>, Integer>> toList() {
            FnList<FnTupl2<FnList<Character>, Integer>> result = FnListSUtil.nil();
            result = inorder(root, result);
            return result;
        }

        private FnList<FnTupl2<FnList<Character>, Integer>> inorder(Node node, FnList<FnTupl2<FnList<Character>, Integer>> acc) {
            if (node == null) return acc;
            acc = inorder(node.rchild, acc);
            acc = FnListSUtil.cons(new FnTupl2<>(node.key, node.value), acc);
            acc = inorder(node.lchild, acc);
            return acc;
        }
    }

    private static int compareWords(FnList<Character> w1, FnList<Character> w2) {
        FnList<Character> c1 = w1, c2 = w2;
        while (c1.consq() && c2.consq()) {
            char ch1 = c1.hd(), ch2 = c2.hd();
            if (ch1 < ch2) return -1;
            if (ch1 > ch2) return 1;
            c1 = c1.tl(); c2 = c2.tl();
        }
        if (c1.nilq() && c2.nilq()) return 0;
        if (c1.nilq()) return -1;
        return 1;
    }

    private static <T> FnList<T> mergeSort(FnList<T> xs, java.util.function.ToIntBiFunction<T,T> cmp) {
        if (xs.nilq() || xs.tl().nilq()) return xs;
        int len = xs.length(), mid = len / 2;
        FnList<T> left = FnListSUtil.nil(), right = FnListSUtil.nil();
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

    static FnList<FnTupl2<FnList<Character>, Integer>> pg2701_word$count$listize4() {
        LnStrm<FnList<Character>> wordStream = Final_01.pg2701_word$strmize();
        RBSTMap map = new RBSTMap();
        LnStcn<FnList<Character>> node = wordStream.eval0();
        while (node != null && !node.nilq()) {
            FnList<Character> word = node.hd();
            Integer count = map.get(word);
            if (count == null) map.put(word, 1);
            else map.put(word, count + 1);
            node = node.tl().eval0();
        }
        FnList<FnTupl2<FnList<Character>, Integer>> WNS = map.toList();
        return mergeSort(WNS, (p1, p2) -> {
            int n1 = p1.sub1, n2 = p2.sub1;
            if (n1 > n2) return -1;
            if (n1 < n2) return 1;
            return compareWords(p1.sub0, p2.sub0);
        });
    }

    public static void main(String[] args) {
        FnList<FnTupl2<FnList<Character>, Integer>> result = pg2701_word$count$listize4();
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