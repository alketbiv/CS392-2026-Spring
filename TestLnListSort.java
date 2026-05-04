import MyLibrary.LnList.*;
public class TestLnListSort {
    public static void main(String[] args) {
        LnList<Integer> xs = new LnList<>();
        xs = new LnList<>(5, xs);
        xs = new LnList<>(3, xs);
        xs = new LnList<>(7, xs);
        xs = new LnList<>(1, xs);
        xs = new LnList<>(4, xs);

        System.out.print("Before: ");
        xs.foritm1(x -> System.out.print(x + " "));
        System.out.println();

        LnList<Integer> sorted = LnListSUtil.insertSort(xs);
        System.out.print("InsertSort: ");
        sorted.foritm1(x -> System.out.print(x + " "));
        System.out.println();

        LnList<Integer> xs2 = new LnList<>();
        xs2 = new LnList<>(5, xs2);
        xs2 = new LnList<>(3, xs2);
        xs2 = new LnList<>(7, xs2);
        xs2 = new LnList<>(1, xs2);
        xs2 = new LnList<>(4, xs2);

        LnList<Integer> sorted2 = LnListSUtil.mergeSort(xs2);
        System.out.print("MergeSort: ");
        sorted2.foritm1(x -> System.out.print(x + " "));
        System.out.println();
    }
}