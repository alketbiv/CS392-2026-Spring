//
// HX-2026-04-28: 50 points
//
// This question tests your understanding
// of recursion and time analysis involving
// recursion.
// Given a sequence xs, a subsequence of xs
// can be represented as a list of integers
// (representing indices). For instance, given
// xs = "Hello", (0, 2, 4) refers to the subeqence
// "Hlo" (since xs[0] = 'H', xs[2] = 'l', and
// xs[4] = 'o'); (0, 3, 4) also refers to "Hlo".
// The subsequece (0, 2, 4) is to the left of
// the subsequece (0, 3, 4) as (0, 2, 4) is less
// than (0, 3, 4) according to the lexicographic
// ordering.
//
// Here you are asked to implement a function that
// finds the longest leftmost ascending subsequence
// of a given sequence.
// For instance, suppose xs = [1,2,1,2,3,1,2,3,4],
// the longest leftmost ascending subsequence of xs
// is represented by (0, 1, 3, 4, 7, 8) (which refers
// to [1,2,2,3,3,4] in xs).
//
// In order to receive 50 points, your implementation
// should be quadratic time, that is, O(n^2) time and
// you MUST give a brief explanation as to why it is so.
// Otherwise, a working solution receives at most 60%, that
// is, 30 points out of 50 points.
//
import MyLibrary.FnList.*;
import MyLibrary.FnA1sz.*;

public class Quiz02_01 {
    public static
    <T extends Comparable<T>>
    FnList<Integer> FnA1szLongestMonoSubsequence(FnA1sz<T> xs) {
        int n = xs.length();
        int[] dp = new int[n];
        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            prev[i] = -1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (xs.getAt(j).compareTo(xs.getAt(i)) <= 0) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        prev[i] = j;
                    }
                }
            }
        }

        int maxLen = 0;
        int maxIdx = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxIdx = i;
            }
        }

        FnList<Integer> result = FnListSUtil.nil();
        int idx = maxIdx;
        while (idx != -1) {
            result = FnListSUtil.cons(idx, result);
            idx = prev[idx];
        }

        return result;
    }

    public static void main(String[] args) {
        // HX-2025-11-19:
        // Please write minimal testing code for FnA1szLongestMonoSubsequence
        Integer[] vals = {1,2,1,2,3,1,2,3,4};
        FnList<Integer> fl = FnListSUtil.nil();
        for (int i = vals.length-1; i >= 0; i--) {
            fl = FnListSUtil.cons(vals[i], fl);
        }
        FnA1sz<Integer> xs = new FnA1sz<Integer>(fl);

        FnList<Integer> result = FnA1szLongestMonoSubsequence(xs);
        System.out.print("Indices: ");
        while (result.consq()) {
            System.out.print(result.hd() + " ");
            result = result.tl();
        }
        System.out.println();
        // expect: 0 1 3 4 7 8

        return /*void*/;
    }
} // end of [public class Quiz02_01{...}]

/*
Time Complexity: O(n^2)
The outer loop runs n times. For each i, the inner loop runs i times.
Total operations: 1+2+...+(n-1) = n*(n-1)/2 = O(n^2).
Space Complexity: O(n) for the dp and prev arrays.
*/