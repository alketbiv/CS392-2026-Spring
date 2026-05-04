import java.util.Comparator;

public class TestAssign10_01 {
    public static void main(String[] args) {
MyPQueueArray<Integer> pq = new MyPQueueArray<Integer>(10, Comparator.naturalOrder());
        // Test isEmpty
        System.out.println("isEmpty (expect true): " + pq.isEmpty());
        System.out.println("size (expect 0): " + pq.size());

        // Test enque
        pq.enque$raw(5);
        pq.enque$raw(3);
        pq.enque$raw(7);
        pq.enque$raw(1);
        pq.enque$raw(4);
        System.out.println("size (expect 5): " + pq.size());

        // Test top - should be smallest
        System.out.println("top (expect 1): " + pq.top$raw());

        // Test deque - should come out in order
        System.out.println("deque (expect 1): " + pq.deque$raw());
        System.out.println("deque (expect 3): " + pq.deque$raw());
        System.out.println("deque (expect 4): " + pq.deque$raw());
        System.out.println("deque (expect 5): " + pq.deque$raw());
        System.out.println("deque (expect 7): " + pq.deque$raw());

        System.out.println("isEmpty (expect true): " + pq.isEmpty());
    }
}