//
// HX-2026-04-28: 30 points
// (plus up to 20 bonus points)
// This is more of a theory problem
// than a programming one.
//
class InvalidRBTException extends RuntimeException {
    public InvalidRBTException(String message) {
        super(message);
    }
}

public class Quiz02_05 {
    public class RBTnode {
        int key;
        int color; // Red = 0; Black = 1
        RBTnode lchild;
        RBTnode rchild;
    }

    private static int checkRBT(RBTnode node, boolean parentRed) {
        if (node == null) return 0;
        if (parentRed && node.color == 0)
            throw new InvalidRBTException("Red violation at key: " + node.key);
        int leftBH = checkRBT(node.lchild, node.color == 0);
        int rightBH = checkRBT(node.rchild, node.color == 0);
        if (leftBH != rightBH)
            throw new InvalidRBTException("Black height mismatch at key: " + node.key);
        return leftBH + (node.color == 1 ? 1 : 0);
    }

    public static boolean isRBT(RBTnode rbt) {
        if (rbt == null) return true;
        if (rbt.color != 1)
            throw new InvalidRBTException("Root must be black");
        try {
            checkRBT(rbt, false);
            return true;
        } catch (InvalidRBTException e) {
            return false;
        }
    }

    static RBTnode[] nodes;

    public static boolean genRedBLackBST() {
        int n = 1000000;
        nodes = new RBTnode[n];
        Quiz02_05 q = new Quiz02_05();

        for (int i = 0; i < n; i++) {
            nodes[i] = q.new RBTnode();
            nodes[i].key = i;
        }

        int level = 0;
        int levelStart = 0;
        int levelSize = 1;
        for (int i = 0; i < n; i++) {
            if (i >= levelStart + levelSize) {
                levelStart += levelSize;
                levelSize *= 2;
                level++;
            }
            nodes[i].color = (level % 2 == 0) ? 1 : 0;
        }

        for (int i = 0; i < n; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < n) nodes[i].lchild = nodes[left];
            if (right < n) nodes[i].rchild = nodes[right];
        }

        RBTnode root = nodes[0];
        System.out.println("Root key: " + root.key);
        System.out.println("Root color (1=black): " + root.color);
        System.out.println("Root left color (0=red): " + root.lchild.color);
        System.out.println("Root left left color (1=black): " + root.lchild.lchild.color);
        return true;
    }

    public static void main(String[] args) {
        Quiz02_05 q = new Quiz02_05();

        try {
            System.out.println("null isRBT (expect true): " + isRBT(null));

            RBTnode single = q.new RBTnode();
            single.key = 1; single.color = 1;
            System.out.println("single black node isRBT (expect true): " + isRBT(single));

            try {
                RBTnode redRoot = q.new RBTnode();
                redRoot.key = 1; redRoot.color = 0;
                isRBT(redRoot);
            } catch (InvalidRBTException e) {
                System.out.println("single red node caught exception: " + e.getMessage());
            }

            System.out.println("genRedBlackBST:");
            genRedBLackBST();

        } catch (InvalidRBTException e) {
            System.out.println("RBT Exception: " + e.getMessage());
        }

        return /*void*/;
    }
}

/*
Explanation of genRedBlackBST:
We build a complete binary tree with 1M nodes using an array.
Colors are assigned level by level:
- Level 0 (root): Black
- Level 1: Red
- Level 2: Black
- Level 3: Red
- ...alternating

This gives minimal black height because:
1. Root must be black (RBT property)
2. No two consecutive red nodes allowed (RBT property)
3. By making every other level red, we maximize red nodes
4. This minimizes black height to approximately log2(n)/2
5. For n=1M nodes, black height is approximately 10

InvalidRBTException is thrown with a specific message indicating
exactly where and why the RBT property is violated.
*/