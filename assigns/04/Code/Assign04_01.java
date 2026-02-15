/*
HX-2026-02-13: 10 points
*/
import MyLibrary.FnList.*;
import MyLibrary.FnStrn.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class Assign04_01 {

    static boolean balencedq(String text) {

        // stack holds opening brackets
        AtomicReference<FnList<Character>> stack =
            new AtomicReference<>(new FnList<Character>());

        // becomes false on first mismatch
        AtomicBoolean ok = new AtomicBoolean(true);

        // use higher-order function (no loops allowed)
        FnStrn.forall(text, (Character c) -> {

            // if already invalid, keep scanning but ignore
            if (!ok.get()) return true;

            // opening brackets -> push
            if (c == '(' || c == '[' || c == '{') {
                stack.set(new FnList<Character>(c, stack.get()));
                return true;
            }

            // closing brackets
            if (stack.get().nilq()) {
                ok.set(false);
                return true;
            }

            char top = stack.get().hd();
            stack.set(stack.get().tl());

            if (c == ')' && top != '(') ok.set(false);
            if (c == ']' && top != '[') ok.set(false);
            if (c == '}' && top != '{') ok.set(false);

            return true;
        });

        return ok.get() && stack.get().nilq();
    }

    public static void main(String[] argv) {

        System.out.println(balencedq("({()[({})]})")); // true
        System.out.println(balencedq("({()[({})])}")); // false
        System.out.println(balencedq("()[]{}"));       // true
        System.out.println(balencedq("([)]"));         // false
    }
}
