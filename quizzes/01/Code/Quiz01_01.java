import MyLibrary.FnA1sz.*;
import MyLibrary.FnList.*;

public class Quiz01_01 {
    public static
    <T extends Comparable<T>>
    int FnA1szBinarySearch(FnA1sz<T> A, T key) {
        int l = 0;
        int r = A.length() - 1;
        int ans = -1;

        while (l <= r) {
            int m = l + (r - l) / 2;
            T x0 = A.getAt(m);

            if (key.compareTo(x0) >= 0) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        return ans;
    }

    public static void main (String[] args) {
        FnList<Integer> xs1 =
            new FnList<Integer>(1,
            new FnList<Integer>(3,
            new FnList<Integer>(3,
            new FnList<Integer>(5,
            new FnList<Integer>(8,
            new FnList<Integer>())))));

        FnA1sz<Integer> A1 = new FnA1sz<Integer>(xs1);

        System.out.println(FnA1szBinarySearch(A1, 0)); // -1
        System.out.println(FnA1szBinarySearch(A1, 1)); // 0
        System.out.println(FnA1szBinarySearch(A1, 3)); // 2
        System.out.println(FnA1szBinarySearch(A1, 4)); // 2
        System.out.println(FnA1szBinarySearch(A1, 8)); // 4
        System.out.println(FnA1szBinarySearch(A1, 9)); // 4

        FnList<String> xs2 =
            new FnList<String>("aa",
            new FnList<String>("bb",
            new FnList<String>("bb",
            new FnList<String>("dd",
            new FnList<String>()))));

        FnA1sz<String> A2 = new FnA1sz<String>(xs2);

        System.out.println(FnA1szBinarySearch(A2, "a"));  // -1
        System.out.println(FnA1szBinarySearch(A2, "aa")); // 0
        System.out.println(FnA1szBinarySearch(A2, "bb")); // 2
        System.out.println(FnA1szBinarySearch(A2, "cc")); // 2
        System.out.println(FnA1szBinarySearch(A2, "dd")); // 3
        System.out.println(FnA1szBinarySearch(A2, "zz")); // 3

        return /*void*/;
    }
}
