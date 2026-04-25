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

    public static void main(String[] args) {
        // Test 1: already sorted
        LnList<Integer> xs1 = new LnList<>(1, new LnList<>(2, new LnList<>(3, new LnList<>())));
        LnList<Integer> sorted1 = LnListInsertSort(xs1);
        System.out.print("Test1: ");
        while (sorted1.consq1()) { System.out.print(sorted1.hd1() + " "); sorted1 = sorted1.tl1(); }
        System.out.println();

        // Test 2: reverse sorted
        LnList<Integer> xs2 = new LnList<>(3, new LnList<>(2, new LnList<>(1, new LnList<>())));
        LnList<Integer> sorted2 = LnListInsertSort(xs2);
        System.out.print("Test2: ");
        while (sorted2.consq1()) { System.out.print(sorted2.hd1() + " "); sorted2 = sorted2.tl1(); }
        System.out.println();

        // Test 3: single element
        LnList<Integer> xs3 = new LnList<>(42, new LnList<>());
        LnList<Integer> sorted3 = LnListInsertSort(xs3);
        System.out.print("Test3: ");
        while (sorted3.consq1()) { System.out.print(sorted3.hd1() + " "); sorted3 = sorted3.tl1(); }
        System.out.println();
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
