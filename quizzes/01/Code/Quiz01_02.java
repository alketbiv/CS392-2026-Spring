import java.util.HashMap;

public class Quiz01_02 {

    public static boolean solve_3prod(Integer[] A) {
        int n = A.length;

        HashMap<Integer, Integer> freq = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i += 1) {
            int x = A[i];
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        }

        for (int i = 0; i < n; i += 1) {
            for (int j = i + 1; j < n; j += 1) {
                long prodLong = (long) A[i] * (long) A[j];

                if (prodLong < Integer.MIN_VALUE || prodLong > Integer.MAX_VALUE) {
                    continue;
                }

                int prod = (int) prodLong;

                if (!freq.containsKey(prod)) {
                    continue;
                }

                int need = 1;
                if (A[i].intValue() == prod) need += 1;
                if (A[j].intValue() == prod) need += 1;

                if (freq.get(prod) >= need) {
                    return true;
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
