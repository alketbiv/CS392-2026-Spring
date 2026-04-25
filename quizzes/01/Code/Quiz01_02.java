public class Quiz01_02 {

    private static int[] toSorted(Integer[] A) {
        int[] s = new int[A.length];
        for (int i = 0; i < A.length; i++) s[i] = A[i];
        for (int i = 1; i < s.length; i++) {
            int key = s[i], j = i - 1;
            while (j >= 0 && s[j] > key) { s[j+1] = s[j]; j--; }
            s[j+1] = key;
        }
        return s;
    }

    public static boolean solve_3prod(Integer[] A) {
        int n = A.length;
        int[] s = toSorted(A);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                long prod = (long) s[i] * s[j];
                if (prod < Integer.MIN_VALUE || prod > Integer.MAX_VALUE) continue;
                int target = (int) prod;
                int lo = 0, hi = n - 1;
                while (lo <= hi) {
                    int mid = (lo + hi) / 2;
                    if (s[mid] == target) {
                        if (mid != i && mid != j) return true;
                        if (mid > 0 && s[mid-1] == target && mid-1 != i && mid-1 != j) return true;
                        if (mid < n-1 && s[mid+1] == target && mid+1 != i && mid+1 != j) return true;
                        break;
                    } else if (s[mid] < target) lo = mid + 1;
                    else hi = mid - 1;
                }
            }
        }
        return false;
    }

    public static void main(String[] argv) {
        Integer[] A1 = {2, 3, 6};
        Integer[] A2 = {2, 2, 4};
        Integer[] A3 = {0, 5, 0};
        Integer[] A4 = {1, 2, 3, 7};
        Integer[] A5 = {-2, 3, -6};
        Integer[] A6 = {4, 4, 4};

        System.out.println(solve_3prod(A1)); // true
        System.out.println(solve_3prod(A2)); // true
        System.out.println(solve_3prod(A3)); // true
        System.out.println(solve_3prod(A4)); // false
        System.out.println(solve_3prod(A5)); // true
        System.out.println(solve_3prod(A6)); // false
    }
}

/*
Time Complexity:
Sort the array in O(n log n), then for each pair (i,j) do a binary search.
O(n^2) pairs * O(log n) search = O(n^2 log n). Better than O(n^3).

Space Complexity: O(n) for the sorted copy.
*/