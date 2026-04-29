package MyLibrary.MyPQueue;


import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.BiConsumer;

public class MyPQueueArray<T> extends MyPQueueBase<T> {

    private Object[] heap;
    private int size0;
    private int capacity;
    private Comparator<T> cmp;

    public MyPQueueArray(int capacity, Comparator<T> cmp) {
        this.capacity = capacity;
        this.heap = new Object[capacity];
        this.size0 = 0;
        this.cmp = cmp;
    }

    public int size() {
        return size0;
    }

    public boolean isFull() {
        return size0 >= capacity;
    }

    @SuppressWarnings("unchecked")
    private T get(int i) {
        return (T) heap[i];
    }

    private void swap(int i, int j) {
        Object tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    private void siftUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (cmp.compare(get(i), get(parent)) < 0) {
                swap(i, parent);
                i = parent;
            } else break;
        }
    }

    private void siftDown(int i) {
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int smallest = i;
            if (left < size0 && cmp.compare(get(left), get(smallest)) < 0)
                smallest = left;
            if (right < size0 && cmp.compare(get(right), get(smallest)) < 0)
                smallest = right;
            if (smallest != i) {
                swap(i, smallest);
                i = smallest;
            } else break;
        }
    }

    public void enque$raw(T itm) {
        heap[size0] = itm;
        siftUp(size0);
        size0++;
    }

    public T top$raw() {
        return get(0);
    }

    public T deque$raw() {
        T top = get(0);
        size0--;
        heap[0] = heap[size0];
        heap[size0] = null;
        siftDown(0);
        return top;
    }

}
