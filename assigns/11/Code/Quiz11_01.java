//
// HX-2026-04-21: 50 points
//
// Please see lectures/lecture-04-21 for an
// example using DFirstEnumerate/BFirstEnumerate
//
// Some "hard" Sudoku puzzles can be
// found here: https://sudoku.com/hard/.
// You are asked to use DFirstEnumerate and BFirstEnumerate
// in FnGtree to solve Sudoku puzzles. Your solution should
// be able to solve "hard" Sudoku puzzles effectively.
//
import Library00.FnList.*;
import Library00.LnStrm.*;
import Library00.FnGtree.*;

class Sudoku implements FnGtree<Sudoku> {
    static final int N = 9;
    int[] board; // 81 cells, 0 = empty

    public Sudoku(int[] board) {
        this.board = board;
    }

    public Sudoku value() {
        return this;
    }

    private boolean isValid(int[] b, int pos, int num) {
        int row = pos / N;
        int col = pos % N;
        // check row
        for (int c = 0; c < N; c++) {
            if (b[row * N + c] == num) return false;
        }
        // check col
        for (int r = 0; r < N; r++) {
            if (b[r * N + col] == num) return false;
        }
        // check 3x3 box
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;
        for (int r = boxRow; r < boxRow + 3; r++) {
            for (int c = boxCol; c < boxCol + 3; c++) {
                if (b[r * N + c] == num) return false;
            }
        }
        return true;
    }

    public FnList<FnGtree<Sudoku>> children() {
        FnList<FnGtree<Sudoku>> res = FnListSUtil.nil();
        // find first empty cell
        int pos = -1;
        for (int i = 0; i < N * N; i++) {
            if (board[i] == 0) { pos = i; break; }
        }
        if (pos == -1) return res; // no empty cell = solved
        for (int num = 1; num <= N; num++) {
            if (isValid(board, pos, num)) {
                int[] newBoard = board.clone();
                newBoard[pos] = num;
                res = FnListSUtil.cons(new Sudoku(newBoard), res);
            }
        }
        return res.reverse();
    }

    public boolean isSolved() {
        for (int i = 0; i < N * N; i++) {
            if (board[i] == 0) return false;
        }
        return true;
    }

    public void print() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                System.out.print(board[r * N + c] + " ");
                if (c == 2 || c == 5) System.out.print("| ");
            }
            System.out.println();
            if (r == 2 || r == 5) System.out.println("------+-------+------");
        }
        System.out.println();
    }
}

public class Quiz11_01 {
    public LnStrm<Sudoku> Soduku_dfs_solve(Sudoku puzzle) {
        return FnGtreeSUtil.DFirstEnumerate(puzzle).filter0((s) -> s.isSolved());
    }
    public LnStrm<Sudoku> Soduku_bfs_solve(Sudoku puzzle) {
        return FnGtreeSUtil.BFirstEnumerate(puzzle).filter0((s) -> s.isSolved());
    }
//
    public static void main(String[] args) {
        // Please add minimal testing code for Sudoku_dfs_solve
        // Please add minimal testing code for Sudoku_bfs_solve

        // Hard sudoku puzzle (0 = empty)
        int[] puzzle = {
            8,0,0, 0,0,0, 0,0,0,
            0,0,3, 6,0,0, 0,0,0,
            0,7,0, 0,9,0, 2,0,0,
            0,5,0, 0,0,7, 0,0,0,
            0,0,0, 0,4,5, 7,0,0,
            0,0,0, 1,0,0, 0,3,0,
            0,0,1, 0,0,0, 0,6,8,
            0,0,8, 5,0,0, 0,1,0,
            0,9,0, 0,0,0, 4,0,0
        };

        Quiz11_01 q = new Quiz11_01();
        Sudoku board = new Sudoku(puzzle);

        // Test DFS solve
        System.out.println("DFS Solution:");
        int[] nsol = {0};
        q.Soduku_dfs_solve(board).foritm0(
            (s) -> {
                nsol[0] += 1;
                if (nsol[0] == 1) s.print();
            }
        );

        // Test BFS solve
        System.out.println("BFS Solution:");
        int[] nsol2 = {0};
        q.Soduku_bfs_solve(board).foritm0(
            (s) -> {
                nsol2[0] += 1;
                if (nsol2[0] == 1) s.print();
            }
        );

        return /*void*/;
    }
//
}
