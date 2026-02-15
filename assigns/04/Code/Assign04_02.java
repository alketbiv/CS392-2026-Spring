/*
HX-2026-02-13: 20 points
*/

import Library00.FnList.*;
import Library00.FnStrn.*;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Assign04_02 {

  static FnStrn FnList$FnStrn_concate(FnList<FnStrn> xs) {

    // 1) compute total length
    AtomicInteger total = new AtomicInteger(0);

    FnListSUtil listU = new FnListSUtil();
    FnStrnSUtil strU  = new FnStrnSUtil();

    listU.foritm(xs, (FnStrn s) -> {
      total.addAndGet(s.length());
    });

    // 2) allocate output buffer and fill it
    char[] out = new char[total.get()];
    AtomicInteger idx = new AtomicInteger(0);

    listU.foritm(xs, (FnStrn s) -> {
      // copy chars of s into out starting at idx
      strU.foritm(s, (Character c) -> {
        out[idx.getAndIncrement()] = c.charValue();
      });
    });

    return new FnStrn(out);
  }

  public static void main(String[] argv) {

    // build ("a", "bc", "def")
    FnList<FnStrn> xs =
      new FnList<>(
        new FnStrn("a"),
        new FnList<>(
          new FnStrn("bc"),
          new FnList<>(
            new FnStrn("def"),
            new FnList<>()
          )
        )
      );

    FnStrn res = FnList$FnStrn_concate(xs);

    // print result using foritm (no loops)
    FnStrnSUtil SU = new FnStrnSUtil();
    AtomicReference<StringBuilder> sb =
      new AtomicReference<>(new StringBuilder());

    SU.foritm(res, (Character c) -> {
      sb.get().append(c.charValue());
    });

    System.out.println(sb.get().toString()); // should print: abcdef
  }
}


