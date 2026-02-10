public class Assign02_01 {
    /*
      HX-2025-02-13: 10 points
      The 'int' type in Java is for integers of some fixed precision.
      More precisely, each integer of the type int is represented a sequence of bits
      of some fixed length. Please write a program that finds this length. Your program
      is expected return the correct answer instantly. Note that you can only use arithmetic
      operations here. In particular, NO BIT-WISE OPERATIONS ARE ALLOWED.
    */
    public static void main(String[] args) {
        // Please give your implementation here

        int x = 1;
        int bits = 0;

        while (x != 0) {
             x = x * 2;
             bits++;
        }

        System.out.println(bits);

    }
}
