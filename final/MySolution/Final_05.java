/*
// HX: 50 points for Final_05
*/

import MyLibrary.LnList.*;
import MyLibrary.MyPQueue.*;
import java.util.function.ToIntBiFunction;

public class Final_05 {

    public static<T> LnList<T>
    LnList_n$way$merge(LnList<T> xss[], ToIntBiFunction<T,T> cmp) {
        MyPQueueArray<int[]> pq = new MyPQueueArray<int[]>(
            xss.length,
            (a, b) -> cmp.applyAsInt((T)xss[a[0]].hd1(), (T)xss[b[0]].hd1())
        );
        for (int i = 0; i < xss.length; i++) {
            if (xss[i] != null && xss[i].consq1()) {
                pq.enque$raw(new int[]{i});
            }
        }
        LnList<T> result = new LnList<T>();
        LnList<T> last = null;
        while (!pq.isEmpty()) {
            int[] idx = pq.deque$raw();
            int i = idx[0];
            LnList<T> node = xss[i];
            LnList<T> next = node.tl1();
            xss[i] = next;
            node.unlink1();
            if (result.nilq1()) {
                result = node;
                last = node;
            } else {
                last.link1(node);
                last = node;
            }
            if (next.consq1()) {
                pq.enque$raw(new int[]{i});
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static<T>
    LnList<T>
    LnList_mergeSort$100way(LnList<T> xs, ToIntBiFunction<T,T> cmp) {
        if (xs.nilq1() || xs.tl1().nilq1()) return xs;
        int len = xs.length1();
        if (len <= 100) return insertionSort(xs, cmp);
        LnList<T>[] parts = new LnList[100];
        for (int i = 0; i < 100; i++) parts[i] = new LnList<T>();
        LnList<T> current = xs;
        int i = 0;
        while (current.consq1()) {
            LnList<T> node = current;
            current = current.tl1();
            node.unlink1();
            int bucket = i % 100;
            node.link1(parts[bucket]);
            parts[bucket] = node;
            i++;
        }
        for (int j = 0; j < 100; j++) {
            parts[j] = parts[j].reverse0();
            parts[j] = LnList_mergeSort$100way(parts[j], cmp);
        }
        return LnList_n$way$merge(parts, cmp);
    }

    private static<T> LnList<T> insertionSort(LnList<T> xs, ToIntBiFunction<T,T> cmp) {
        LnList<T> sorted = new LnList<T>();
        while (xs.consq1()) {
            LnList<T> node = xs;
            xs = xs.tl1();
            node.unlink1();
            sorted = insert(sorted, node, cmp);
        }
        return sorted;
    }

    private static<T> LnList<T> insert(LnList<T> sorted, LnList<T> node, ToIntBiFunction<T,T> cmp) {
        if (sorted.nilq1() || cmp.applyAsInt(node.hd1(), sorted.hd1()) <= 0) {
            node.link1(sorted);
            return node;
        }
        LnList<T> prev = sorted;
        LnList<T> curr = sorted.tl1();
        while (curr.consq1() && cmp.applyAsInt(node.hd1(), curr.hd1()) > 0) {
            prev = curr;
            curr = curr.tl1();
        }
        prev.unlink1();
        node.link1(curr);
        prev.link1(node);
        return sorted;
    }

    public static void main(String[] args) {
        LnList<Integer> list = new LnList<Integer>();
        for (int j = 999999; j >= 0; j--) {
            list = new LnList<Integer>(j, list);
        }
        ToIntBiFunction<Integer, Integer> parityCmp = (a, b) -> {
            boolean aEven = (a % 2 == 0);
            boolean bEven = (b % 2 == 0);
            if (aEven && !bEven) return -1;
            if (!aEven && bEven) return 1;
            return Integer.compare(a, b);
        };
        LnList<Integer> sorted = LnList_mergeSort$100way(list, parityCmp);
        int count = 0;
        while (sorted.consq1() && count < 20) {
            System.out.print(sorted.hd1() + " ");
            sorted = sorted.tl1();
            count++;
        }
        System.out.println();
        return /*void*/;
    }
}