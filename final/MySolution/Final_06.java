//
// HX: 50 bonus points
//
public class Final_06 {
    public static
    <T extends Comparable<T>>
    void sort1000WithNoRecursion(T[] A) {
        insertSort(A, 1);
    }

    private static
    <T extends Comparable<T>>
    void insertSort(T[] A, int i) {
        if (i >= A.length) return;
        insert(A, i, A[i]);
        insertSort(A, i + 1);
    }

    private static
    <T extends Comparable<T>>
    void insert(T[] A, int i, T key) {
        if (i <= 0 || A[i-1].compareTo(key) <= 0) {
            A[i] = key; return;
        }
        A[i] = A[i-1];
        insert(A, i - 1, key);
    }

    public static void main(String[] args) {
        Integer[] A1 = {5, 3, 1, 4, 2};
        sort1000WithNoRecursion(A1);
        System.out.print("Test1: ");
        for (int i = 0; i < A1.length; i++) System.out.print(A1[i] + " ");
        System.out.println();

        Integer[] A2 = {5, 4, 3, 2, 1};
        sort1000WithNoRecursion(A2);
        System.out.print("Test2: ");
        for (int i = 0; i < A2.length; i++) System.out.print(A2[i] + " ");
        System.out.println();

        return /*void*/;
    }
}