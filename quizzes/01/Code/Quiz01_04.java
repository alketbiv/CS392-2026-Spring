//
// HX: 50 points
//
public class Quiz01_04 {

    public static
    <T extends Comparable<T>>
    LnList<T> LnListInsertSort(LnList<T> xs) {
        if (xs.nilq1()) {
            return xs;
        }

        // Take the first node as the initial sorted list.
        LnList<T> rest = xs.unlink();
        LnList<T> sorted = xs;
        xs = rest;

        while (xs.consq1()) {
            // Extract one node from xs as a singleton list [cur]
            LnList<T> cur = xs;
            xs = xs.unlink();

            T x0 = cur.hd1();

            // Insert at front if needed
            if (x0.compareTo(sorted.hd1()) < 0) {
                cur.link(sorted);
                sorted = cur;
            } else {
                // Stable insertion:
                // move past all elements <= x0
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

    public static void main (String[] args) {
        // Test 1: nearly sorted list of 1M elements
        int n = 10000;
        LnList<Integer> xs = new LnList<Integer>();

        for (int i = n - 1; i >= 0; i -= 1) {
            if (i == 500001) {
                xs = new LnList<Integer>(500001, xs);
                xs = new LnList<Integer>(500000, xs);
                i = 499999;
            } else {
                xs = new LnList<Integer>(i, xs);
            }
        }

        long t1 = System.currentTimeMillis();
        LnList<Integer> ys = LnListInsertSort(xs);
        long t2 = System.currentTimeMillis();
        System.out.println("1M nearly-sorted list done in ms = " + (t2 - t1));

        // Print first few items
        final int[] c1 = {0};
        ys.foritm1(x -> {
            if (c1[0] < 10) {
                System.out.print(x + " ");
            }
            c1[0] += 1;
        });
        System.out.println();

        // Test 2: parity sorting to check stability
        class ParityItem implements Comparable<ParityItem> {
            int value;
            int id;

            ParityItem(int value, int id) {
                this.value = value;
                this.id = id;
            }

            public int compareTo(ParityItem other) {
                return (this.value % 2) - (other.value % 2);
            }

            public String toString() {
                return "(" + value + "," + id + ")";
            }
        }

        LnList<ParityItem> ps =
            new LnList<ParityItem>(new ParityItem(7, 0),
            new LnList<ParityItem>(new ParityItem(2, 1),
            new LnList<ParityItem>(new ParityItem(9, 2),
            new LnList<ParityItem>(new ParityItem(4, 3),
            new LnList<ParityItem>(new ParityItem(11, 4),
            new LnList<ParityItem>(new ParityItem(6, 5),
            new LnList<ParityItem>(new ParityItem(13, 6),
            new LnList<ParityItem>(new ParityItem(8, 7),
            new LnList<ParityItem>()))))))));

        LnList<ParityItem> qs = LnListInsertSort(ps);
        qs.foritm1(x -> System.out.print(x + " "));
        System.out.println();
    }
}
