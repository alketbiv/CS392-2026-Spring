//
// HX: For testing Quiz01_05
//
import MyLibrary.FnList.*;

public class Quiz01_05_test {
    public static void main (String args[]) {
        FnList<Integer> xs = FnListSUtil.nil();

        for (int i = 999; i >= 0; i -= 1) {
            xs = FnListSUtil.cons(i, xs);
        }

        FnList<Integer> ys =
            Quiz01_05.someRevStableSort
            (
                xs,
                (x1, x2) -> (x1 % 2) - (x2 % 2)
            );

        FnListSUtil.iforitm
        (
            ys,
            (i, x) -> {
                if (i < 40) {
                    System.out.print(x);
                    System.out.print(" ");
                }
            }
        );
        System.out.println();

        System.out.println("length = " + FnListSUtil.length(ys));
    }
}