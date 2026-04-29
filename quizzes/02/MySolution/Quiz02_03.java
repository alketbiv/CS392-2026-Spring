//
// HX-2026-04-28: 50 points
//
import MyLibrary.FnList.*;
import MyLibrary.LnStrm.*;
import MyLibrary.FnGtree.*;
import java.util.Comparator;

class UnsupportedOpr extends RuntimeException {
    String opr;
    public UnsupportedOpr(String opr) {
        this.opr = opr;
    }
}

abstract class Term {
    public String tag = "Term";
    public abstract double eval();
    public abstract String toString();
}

class TermInt extends Term {
    public int val;
    public TermInt(int val) {
        this.tag = "TermInt"; this.val = val;
    }
    public double eval() { return val; }
    public String toString() { return "" + val; }
}

class TermOpr extends Term {
    public String opr;
    public Term arg1, arg2;
    public TermOpr(String opr0, Term arg1, Term arg2) {
        this.tag = "TermOpr";
        this.opr = opr0; this.arg1 = arg1; this.arg2 = arg2;
    }
    public double eval() {
        switch (opr) {
            case "+": return arg1.eval() + arg2.eval();
            case "-": return arg1.eval() - arg2.eval();
            case "*": return arg1.eval() * arg2.eval();
            case "/": return arg1.eval() / arg2.eval();
        }
        throw new UnsupportedOpr(opr);
    }
    public String toString() {
        return "(" + arg1.toString() + " " + opr + " " + arg2.toString() + ")";
    }
}

class GameState implements FnGtree<Term> {
    FnList<Term> terms;
    Term last;

    public GameState(FnList<Term> terms, Term last) {
        this.terms = terms;
        this.last = last;
    }

    public Term value() { return last; }

    public FnList<FnGtree<Term>> children() {
        FnList<FnGtree<Term>> res = FnListSUtil.nil();
        if (last == null) {
            // initial state - generate all single terms
            FnList<Term> cur = terms;
            while (cur.consq()) {
                Term t = cur.hd();
                FnList<Term> rest = FnListSUtil.nil();
                FnList<Term> tmp = terms;
                while (tmp.consq()) {
                    if (tmp.hd() != t) rest = FnListSUtil.cons(tmp.hd(), rest);
                    tmp = tmp.tl();
                }
                res = FnListSUtil.cons(new GameState(rest, t), res);
                cur = cur.tl();
            }
            return res;
        }
        if (terms.nilq()) return res;
        String[] oprs = {"+", "-", "*", "/"};
        FnList<Term> cur = terms;
        while (cur.consq()) {
            Term t = cur.hd();
            FnList<Term> rest = FnListSUtil.nil();
            FnList<Term> tmp = terms;
            while (tmp.consq()) {
                if (tmp.hd() != t) rest = FnListSUtil.cons(tmp.hd(), rest);
                tmp = tmp.tl();
            }
            for (String op : oprs) {
                Term combined = new TermOpr(op, last, t);
                res = FnListSUtil.cons(new GameState(rest, combined), res);
            }
            cur = cur.tl();
        }
        return res;
    }
}

public class Quiz02_03 {

    private FnList<Term> makeTermList(int n1, int n2, int n3, int n4) {
        FnList<Term> terms = FnListSUtil.nil();
        terms = FnListSUtil.cons(new TermInt(n4), terms);
        terms = FnListSUtil.cons(new TermInt(n3), terms);
        terms = FnListSUtil.cons(new TermInt(n2), terms);
        terms = FnListSUtil.cons(new TermInt(n1), terms);
        return terms;
    }

    public LnStrm<Term> GameOf24_bfs_solve(int n1, int n2, int n3, int n4) {
        FnList<Term> terms = makeTermList(n1, n2, n3, n4);
        GameState root = new GameState(terms, null);
        return FnGtreeSUtil.BFirstEnumerate(root)
            .filter0((t) -> t != null && t.eval() == 24.0);
    }

    public LnStrm<Term> GameOf24_dfs_solve(int n1, int n2, int n3, int n4) {
        FnList<Term> terms = makeTermList(n1, n2, n3, n4);
        GameState root = new GameState(terms, null);
        return FnGtreeSUtil.DFirstEnumerate(root)
            .filter0((t) -> t != null && t.eval() == 24.0);
    }

    public static void main(String[] args) {
        Quiz02_03 q = new Quiz02_03();

        // Test DFS
        System.out.println("DFS solutions for 4 1 8 7:");
        int[] count = {0};
        q.GameOf24_dfs_solve(4, 1, 8, 7).foritm0((t) -> {
            if (count[0] < 3) System.out.println(t.toString());
            count[0]++;
        });
        System.out.println("Total DFS solutions: " + count[0]);

        // Test BFS
        System.out.println("BFS solutions for 4 1 8 7:");
        int[] count2 = {0};
        q.GameOf24_bfs_solve(4, 1, 8, 7).foritm0((t) -> {
            if (count2[0] < 3) System.out.println(t.toString());
            count2[0]++;
        });
        System.out.println("Total BFS solutions: " + count2[0]);

        return /*void*/;
    }
}