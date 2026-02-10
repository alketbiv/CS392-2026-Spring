public class Assign02_03 {
    public static boolean solve_3sum(Integer[] A) {
        // Soft O(n^2):
        // For each k (n choices), do a two-pointer sweep (O(n)) on the sorted prefix.
        // Total is O(n^2). "Soft" because the inner loop is linear per k.
        int n = A.length;
        if (n < 3) return false;

        // A is stated to be sorted already.
        for (int k = 0; k < n; k++) {
            int i = 0;
            int j = k - 1;

            while (i < j) {
                long sum = (long) A[i] + (long) A[j]; // avoid overflow
                long target = A[k];

                if (sum == target) return true;
                if (sum < target) i++;
                else j--;
            }
        }
        return false;
    }

    public static void main(String[] argv) {
        Integer[] A1 = {1, 2, 3, 5, 8, 13};
        System.out.println(solve_3sum(A1)); // true (1+2=3, 5+8=13)

        Integer[] A2 = {1, 4, 6, 10, 15};
        System.out.println(solve_3sum(A2)); // true (4+6=10)

        Integer[] A3 = {1, 4, 6, 11, 15};
        System.out.println(solve_3sum(A3)); // false

        Integer[] A4 = {-10, -3, 0, 1, 2, 7};
        System.out.println(solve_3sum(A4)); // true (-3 + 1 = -2? not present; 0+1=1 invalid distinct?; -10+7=-3 present => true)
    }
}
