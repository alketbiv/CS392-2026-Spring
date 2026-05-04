/*
// HX: 20 points for Final_01
*/

import MyLibrary.FnList.*;
import MyLibrary.LnStrm.*;

public class Final_01 {
    static LnStrm<FnList<Character>> pg2701_word$strmize() {
        LnStrm<Character> charStream = Final_00.pg2701_char$strmize();
        return skipNonWord(charStream);
    }

    private static boolean isWordChar(char ch) {
        return (ch >= 'a' && ch <= 'z') ||
               (ch >= 'A' && ch <= 'Z') ||
               ch == '\'';
    }

    private static char toLower(char ch) {
        if (ch >= 'A' && ch <= 'Z') return (char)(ch - 'A' + 'a');
        return ch;
    }

    private static LnStrm<FnList<Character>> skipNonWord(LnStrm<Character> cs) {
        if (cs == null) return new LnStrm<FnList<Character>>();
        LnStcn<Character> node = cs.eval0();
        if (node == null || node.nilq()) return new LnStrm<FnList<Character>>();
        char ch = node.hd();
        if (isWordChar(ch)) return collectWord(ch, node.tl());
        return skipNonWord(node.tl());
    }

    private static LnStrm<FnList<Character>> collectWord(char first, LnStrm<Character> cs) {
        FnList<Character> word = FnListSUtil.cons(toLower(first), FnListSUtil.nil());
        LnStcn<Character> node = (cs != null) ? cs.eval0() : null;
        while (node != null && !node.nilq() && isWordChar(node.hd())) {
            word = FnListSUtil.cons(toLower(node.hd()), word);
            LnStrm<Character> next = node.tl();
            node = (next != null) ? next.eval0() : null;
        }
        word = FnListSUtil.reverse(word);
        final FnList<Character> finalWord = word;
        // reconstruct rest stream from already-evaluated node
        final LnStcn<Character> restNode = node;
        final LnStrm<Character> rest = new LnStrm<Character>(() -> restNode != null ? restNode : new LnStcn<Character>());
        return new LnStrm<FnList<Character>>(() -> new LnStcn<FnList<Character>>(finalWord, skipNonWord(rest)));
    }

    public static void main(String[] args) {
        LnStrm<FnList<Character>> words = pg2701_word$strmize();
        int count = 0;
        LnStcn<FnList<Character>> node = words.eval0();
        while (node != null && !node.nilq() && count < 20) {
            FnList<Character> word = node.hd();
            word.foritm(ch -> System.out.print(ch));
            System.out.println();
            node = node.tl().eval0();
            count++;
        }
        return /*void*/;
    }
}