//
// HX: 50 points
// Here we revisit a question on quiz01 (Quiz01_03).
// Instead of sorting 10 elements without recursion,
// you are asked to insertion-sort up to 1 million
// elements without recursion.
// Attention:
// You are suppose to do insertion-sort. If you do
// bubble-sort, you can receive up to 60%, that is
// 30 points of 50.
//
public class Quiz02_02 {
    public static
    <T extends Comparable<T>>
    void sort1000WithNoRecursion(T[] A) {
        // HX-2025-11-20:
        // A is an array of size at most 1000K.
        // Please implement a sorting algorithm
        // WITHOUT recursion that can effectively
        // sort A.
        int n = A.length;
        for (int i = 1; i < n; i++) {
            T key = A[i];
            int j = i - 1;
            while (j >= 0 && A[j].compareTo(key) > 0) {
                A[j + 1] = A[j];
                j--;
            }
            A[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        // HX-2025-11-19:
        // Please write minimal testing code for sort1000WithNoRecursion
        Integer[] A1 = {5, 3, 1, 4, 2};
        sort1000WithNoRecursion(A1);
        System.out.print("Test1: ");
        for (int x : A1) System.out.print(x + " ");
        System.out.println(); // expect: 1 2 3 4 5

        Integer[] A2 = {1, 2, 3, 4, 5};
        sort1000WithNoRecursion(A2);
        System.out.print("Test2: ");
        for (int x : A2) System.out.print(x + " ");
        System.out.println(); // expect: 1 2 3 4 5

        Integer[] A3 = {5, 4, 3, 2, 1};
        sort1000WithNoRecursion(A3);
        System.out.print("Test3: ");
        for (int x : A3) System.out.print(x + " ");
        System.out.println(); // expect: 1 2 3 4 5

        return /*void*/;
    }
} // end of [public class Quiz02_02{...}]