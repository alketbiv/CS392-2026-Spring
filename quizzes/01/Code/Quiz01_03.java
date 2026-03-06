import java.util.Arrays;
import java.lang.reflect.Array;

public class Quiz01_03 {
    public static
    <T extends Comparable<T>>
    T[] sort20WithNoRecursion
    (T x00, T x01, T x02, T x03, T x04, T x05, T x06, T x07, T x08, T x09,
     T x10, T x11, T x12, T x13, T x14, T x15, T x16, T x17, T x18, T x19) {

        T[] res = (T[]) Array.newInstance(x00.getClass(), 20);

        res[0]  = x00; res[1]  = x01; res[2]  = x02; res[3]  = x03; res[4]  = x04;
        res[5]  = x05; res[6]  = x06; res[7]  = x07; res[8]  = x08; res[9]  = x09;
        res[10] = x10; res[11] = x11; res[12] = x12; res[13] = x13; res[14] = x14;
        res[15] = x15; res[16] = x16; res[17] = x17; res[18] = x18; res[19] = x19;

        Arrays.sort(res);
        return res;
    }

    public static void main (String[] args) {
        Integer[] A1 =
            sort20WithNoRecursion
            (9, 3, 7, 1, 8, 2, 6, 5, 4, 0,
             19, 13, 17, 11, 18, 12, 16, 15, 14, 10);

        System.out.println(Arrays.toString(A1));

        String[] A2 =
            sort20WithNoRecursion
            ("k", "d", "q", "a", "m", "b", "z", "c", "x", "e",
             "t", "g", "p", "f", "r", "h", "n", "i", "y", "j");

        System.out.println(Arrays.toString(A2));
    }
}
