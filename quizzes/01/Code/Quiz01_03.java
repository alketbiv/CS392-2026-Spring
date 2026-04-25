public class Quiz01_03 {

    @SuppressWarnings("unchecked")
    public static
    <T extends Comparable<T>>
    T[] sort20WithNoRecursion
    (T x00, T x01, T x02, T x03, T x04, T x05, T x06, T x07, T x08, T x09,
     T x10, T x11, T x12, T x13, T x14, T x15, T x16, T x17, T x18, T x19) {

        T[] res = (T[]) new Object[20];

        res[0]  = x00; res[1]  = x01; res[2]  = x02; res[3]  = x03; res[4]  = x04;
        res[5]  = x05; res[6]  = x06; res[7]  = x07; res[8]  = x08; res[9]  = x09;
        res[10] = x10; res[11] = x11; res[12] = x12; res[13] = x13; res[14] = x14;
        res[15] = x15; res[16] = x16; res[17] = x17; res[18] = x18; res[19] = x19;

        // simple bubble sort (no recursion)
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 19 - i; j++) {
                if (res[j].compareTo(res[j + 1]) > 0) {
                    T temp = res[j];
                    res[j] = res[j + 1];
                    res[j + 1] = temp;
                }
            }
        }

        return res;
    }
}

/*
Time Complexity:
Bubble sort runs in O(n^2).
Since n = 20 (constant), it is effectively O(1).

Space Complexity:
O(1)
*/