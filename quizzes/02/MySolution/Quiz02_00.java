//
// HX: 20 points
//
import MyLibrary.FnList.*;
import MyLibrary.FnA1sz.*;
import MyLibrary.FnTuple.*;
import MyLibrary.LnList.*;
import MyLibrary.LnStrm.*;
import MyLibrary.MyMap00.*;
import MyLibrary.FnGtree.*;
import MyLibrary.MyQueue.*;
import MyLibrary.MyStack.*;
import MyLibrary.MyPQueue.*;
import MyLibrary.MyBST.*;
import java.util.Comparator;

public class Quiz02_00 {
    /*
     MyLibrary contains the following classes:
     - FnList: functional list
     - FnA1sz: functional array
     - FnTuple: tuples (FnTupl2, FnTupl3)
     - LnList: linear (mutable) list
     - LnStrm: lazy stream
     - MyMap00: hashmap (separate chaining and open addressing)
     - FnGtree: generic tree for DFS/BFS
     - MyQueue: queue (array-based and list-based)
     - MyStack: stack (array-based and list-based)
     - MyPQueue: priority queue (array-based heap)
     - MyBST: doubly linked binary search tree
     */
    public static void main(String[] args) {
        FnList<Integer> fnList_obj = new FnList<Integer>();
        System.out.println("FnList created: " + fnList_obj.nilq());

        FnA1sz<Integer> fnA1sz_obj = new FnA1sz<Integer>(new FnList<Integer>());
        System.out.println("FnA1sz created: " + fnA1sz_obj.length());

        FnTupl2<String, Integer> fnTupl2_obj = new FnTupl2<>("hello", 42);
        System.out.println("FnTupl2 created: " + fnTupl2_obj.sub0 + ", " + fnTupl2_obj.sub1);

        LnList<Integer> lnList_obj = new LnList<Integer>();
        System.out.println("LnList created: " + lnList_obj.nilq1());

        MyStackList<Integer> myStackList_obj = new MyStackList<Integer>();
        System.out.println("MyStackList isEmpty: " + myStackList_obj.isEmpty());

        MyStackArray<Integer> myStackArray_obj = new MyStackArray<Integer>(10);
        System.out.println("MyStackArray isEmpty: " + myStackArray_obj.isEmpty());

        MyQueueList<Integer> myQueueList_obj = new MyQueueList<Integer>();
        System.out.println("MyQueueList isEmpty: " + myQueueList_obj.isEmpty());

        MyQueueArray<Integer> myQueueArray_obj = new MyQueueArray<Integer>(10);
        System.out.println("MyQueueArray isEmpty: " + myQueueArray_obj.isEmpty());

        MyPQueueArray<Integer> myPQueue_obj = new MyPQueueArray<Integer>(10, Comparator.naturalOrder());
        System.out.println("MyPQueueArray isEmpty: " + myPQueue_obj.isEmpty());

        MyBST myBST_obj = new MyBST();
        System.out.println("MyBST created successfully");

        return /*void*/;
    }
} // end of [class Quiz02_00{...}]
