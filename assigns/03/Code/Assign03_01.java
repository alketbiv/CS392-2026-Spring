/*
  HX-2026-02-05: 10 points
*/

public class Assign03_01 {
    //
    // HX-2025-09-15:
    // This implementation of f91
    // is not tail-recursive. Please
    // translate it into a version that
    // is tail-recursive
    /*
    static int f91(int n) {
        if (n > 100)
            return n-10;
        else
            return f91(f91(n+11));
    }
    */

    static int f91(int n) {
        return f91_tail(n, 0);
    }

    static int f91_tail(int n, int pending) {
        if (n > 100) {
            int v = n - 10;
            if (pending == 0) {
                return v;
            } else {
                return f91_tail(v, pending - 1);
            }
        } else {
            return f91_tail(n + 11, pending + 1);
        }
    }

    public static void main(String[] argv) {
        // Please write some testing code here
        System.out.println(f91(99));
        System.out.println(f91(100));
        System.out.println(f91(101));
        System.out.println(f91(250));
    }
}

