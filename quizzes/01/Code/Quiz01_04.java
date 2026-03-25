public class Quiz01_04 {

    public static
    <T extends Comparable<T>>
    LnList<T> LnListInsertSort(LnList<T> xs) {
        if (xs.nilq1()) {
            return xs;
        }

        LnList<T> rest = xs.unlink();
        LnList<T> sorted = xs;
        xs = rest;

        while (xs.consq1()) {
            LnList<T> cur = xs;
            xs = xs.unlink();

            T x0 = cur.hd1();

            if (x0.compareTo(sorted.hd1()) < 0) {
                cur.link(sorted);
                sorted = cur;
            } else {
                LnList<T> prev = sorted;
                LnList<T> next = sorted.tl1();

                while (next.consq1() && x0.compareTo(next.hd1()) >= 0) {
                    prev = next;
                    next = next.tl1();
                }

                LnList<T> tail = prev.unlink();
                cur.link(tail);
                prev.link(cur);
            }
        }

        return sorted;
    }
}

/*
Time Complexity:
In the worst case, each element may need to be inserted into its proper
position by scanning through the sorted part of the list.
Therefore, the time complexity is O(n^2).

Space Complexity:
The algorithm only rearranges links and uses a constant amount of extra space,
so the space complexity is O(1).
*/
