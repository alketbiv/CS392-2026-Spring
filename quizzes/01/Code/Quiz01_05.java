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

/*
Explanation:
We first reverse the input list, then apply a stable sorting method.
Since the sorting method is stable, equal elements keep their order
in the reversed list. Therefore, their order is reversed relative to
the original list, achieving reverse-stable sorting.
*/