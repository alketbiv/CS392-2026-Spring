/*
HX-2026-02-13: 10 points
*/

import Library00.FnList.*;
import Library00.FnStrn.*;

import java.util.concurrent.atomic.AtomicReference;

public class Assign04_01 {

  static boolean balencedq(String text) {

    // stack holds opening brackets
    AtomicReference<FnList<Character>> stack =
      new AtomicReference<>(new FnList<Character>());

    // turn String into FnStrn
    FnStrn fs = new FnStrn(text);

    // higher-order utility (no loops)
    FnStrnSUtil SU = new FnStrnSUtil();

    boolean ok = SU.forall(fs, (Character c) -> {

      // opening brackets -> push
      if (c == '(' || c == '[' || c == '{') {
        stack.set(new FnList<Character>(c, stack.get()));
        return true;
      }

      // ignore non-bracket chars
      if (c != ')' && c != ']' && c != '}') return true;

      // closing bracket but nothing to match
      if (stack.get().nilq()) return false;

      char top = stack.get().hd();
      stack.set(stack.get().tl());

      if (c == ')' && top != '(') return false;
      if (c == ']' && top != '[') return false;
      if (c == '}' && top != '{') return false;

      return true;
    });

    // must end with empty stack too
    return ok && stack.get().nilq();
  }

  public static void main(String[] argv) {
    System.out.println(balencedq("({()[({})]})")); // true
    System.out.println(balencedq("({()[({})])}")); // false
    System.out.println(balencedq("()[]{}"));       // true
    System.out.println(balencedq("([)]"));         // false
    System.out.println(balencedq(""));             // true
    System.out.println(balencedq("{[()]}"));       // true
  }
}
