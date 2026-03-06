//
// HX: 30 points
//
/*
//
 Reverse-stable sorting is similar to stable sorting:
 The ordering of the equals are reversed in the sorted
 version. For instance, 1^1, 2^1, 3^1, 2^2, 3^2, 1^2
 becomes 1^2, 1^1, 2^2, 2^1, 3^2, 3^1 after sorted in
 the reverse-stable manner. If this is unclear to you,
 please seek clarification on Piazza.
//
//
 No use of external methods (e.g., those from Arrays)
 is allowed here.
//
//
*/
import MyLibrary.FnList.*;
import java.util.function.ToIntBiFunction;

abstract public class Quiz01_05 {
    public static <T>
    FnList<T> someSort
    (FnList<T> xs, ToIntBiFunction<T,T> cmp) {
        return FnListSUtil.insertSort(xs, cmp);
    }

    public static <T>
    FnList<T> someRevStableSort
    (FnList<T> xs, ToIntBiFunction<T,T> cmp) {
        return someSort(FnListSUtil.reverse(xs), cmp);
    }
}