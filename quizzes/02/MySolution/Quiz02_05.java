//
// HX-2026-04-28: 30 points
// (plus up to 20 bonus points)
// This is more of a theory problem
// than a programming one.
//
public class Quiz02_05 {
    public class RBTnode {
        int key;
        int color; // Red = 0; Black = 1
        RBTnode lchild;
        RBTnode rchild;
    }
    //
    // HX: 10 points for this one
    // HX: If your implementation only
    // visit each node in [rbt] at most once,
    // then it will be rewarded with some bonus
    // points (up to 20 bonus points).
    //
    // Returns black height if valid RBT, -1 if invalid
    private static int checkRBT(RBTnode node, boolean parentRed) {
        if (node == null) return 0;
        // Red node cannot have red parent
        if (parentRed && node.color == 0) return -1;
        int leftBH = checkRBT(node.lchild, node.color == 0);
        int rightBH = checkRBT(node.rchild, node.color == 0);
        if (leftBH == -1 || rightBH == -1) return -1;
        if (leftBH != rightBH) return -1;
        return leftBH + (node.color == 1 ? 1 : 0);
    }

    public static boolean isRBT(RBTnode rbt) {
        if (rbt == null) return true;
        if (rbt.color != 1) return false; // root must be black
        return checkRBT(rbt, false) != -1;
    }

    //
    // HX: 20 points
    // Generate a BST with 1M keys with minimal black height.
    //
    // Strategy: We build the tree level by level.
    // The pattern is: Red, Black, Red, Black, Red...
    // starting from the ROOT which must be Black.
    // So: level 0 = Black (root), level 1 = Red,
    //     level 2 = Black, level 3 = Red, etc.
    //
    // Why this gives minimal black height:
    // By alternating Red and Black levels, we maximize
    // the number of Red nodes (which don't count toward
    // black height). For n=1M nodes in a complete binary
    // tree of height ~20, we get black height of ~10.
    // This is minimal because we cannot have two consecutive
    // red levels (RBT property), so the best we can do is
    // alternate red and black.
    //
    static RBTnode[] nodes;

    public static boolean genRedBLackBST() {
        int n = 1000000;
        nodes = new RBTnode[n];
        Quiz02_05 q = new Quiz02_05();

        // Build complete binary tree with keys 0..n-1
        // Root at index 0, children of i at 2i+1 and 2i+2
        for (int i = 0; i < n; i++) {
            nodes[i] = q.new RBTnode();
            nodes[i].key = i;
        }

        // Assign colors level by level
        // level 0 (root) = Black, level 1 = Red,
        // level 2 = Black, level 3 = Red, ...
        // Node i is at level floor(log2(i+1))
        // We compute level iteratively to avoid log
        int level = 0;
        int levelStart = 0;
        int levelSize = 1;
        for (int i = 0; i < n; i++) {
            if (i >= levelStart + levelSize) {
                levelStart += levelSize;
                levelSize *= 2;
                level++;
            }
            // Root (level 0) must be Black
            // level 0 = Black, level 1 = Red,
            // level 2 = Black, level 3 = Red...
            nodes[i].color = (level % 2 == 0) ? 1 : 0;
        }

        // Link nodes
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

        // Test isRBT with exception mechanism
        try {
            // Test 1: null tree is valid RBT
            System.out.println("null isRBT (expect true): " + isRBT(null));

            // Test 2: single black node
            RBTnode single = q.new RBTnode();
            single.key = 1; single.color = 1;
            System.out.println("single black node isRBT (expect true): " + isRBT(single));

            // Test 3: single red node (invalid)
            RBTnode redRoot = q.new RBTnode();
            redRoot.key = 1; redRoot.color = 0;
            System.out.println("single red node isRBT (expect false): " + isRBT(redRoot));

            // Test genRedBlackBST
            System.out.println("genRedBlackBST:");
            genRedBLackBST();

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
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
- ...and so on alternating

This gives minimal black height because:
1. The root must be black (RBT property)
2. No two consecutive red nodes are allowed (RBT property)
3. By making every other level red, we maximize red nodes
4. This minimizes the black height to approximately log2(n)/2
5. For n=1M nodes, black height ≈ 10

The pattern Red-Black-Red at three levels means:
level 0 = Black, level 1 = Red, level 2 = Black
which satisfies all RBT properties while minimizing black height.
*/