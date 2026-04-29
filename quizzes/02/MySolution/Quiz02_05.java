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
        // HX: Please implement a function that
        // tests whether a given RBTnode is a valid
        // red-black tree.
        if (rbt == null) return true;
        if (rbt.color != 1) return false; // root must be black
        return checkRBT(rbt, false) != -1;
    }

    //
    // HX: 20 points
    // Generate a BST with 1M keys with minimal black height.
    // Strategy: build a complete binary tree where all internal
    // nodes are black and all leaf-level nodes are red.
    // This gives minimal black height = floor(log2(n)) / 2
    //
    static RBTnode[] nodes;
    static int nodeCount = 0;

    public static boolean genRedBLackBST() {
        int n = 1000000;
        nodes = new RBTnode[n];
        Quiz02_05 q = new Quiz02_05();

        // Insert keys 0..999999 using array-based complete binary tree
        // Root at index 0, children of i at 2i+1 and 2i+2
        for (int i = 0; i < n; i++) {
            nodes[i] = q.new RBTnode();
            nodes[i].key = i;
            // Assign color: black height minimal means alternating levels
            // Nodes at even depth = black, odd depth = red
            int depth = (int)(Math.log(i + 1) / Math.log(2));
            nodes[i].color = (depth % 2 == 0) ? 1 : 0;
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
        return true;
    }

    public static void main(String[] args) {
        // Please add minimal testing code for isRBT()
        Quiz02_05 q = new Quiz02_05();

        // Test 1: null tree is valid RBT
        System.out.println("null isRBT (expect true): " + isRBT(null));

        // Test 2: single black node
        RBTnode single = q.new RBTnode();
        single.key = 1; single.color = 1;
        System.out.println("single black node isRBT (expect true): " + isRBT(single));

        // Test 3: single red node (invalid - root must be black)
        RBTnode redRoot = q.new RBTnode();
        redRoot.key = 1; redRoot.color = 0;
        System.out.println("single red node isRBT (expect false): " + isRBT(redRoot));

        // Please add minimal testing code for genRedBlackBST()
        System.out.println("genRedBlackBST:");
        genRedBLackBST();

        return /*void*/;
    }
}

/*
Explanation:
The generated RBT has minimal black height because we use a complete binary
tree structure where nodes alternate between black (even depth) and red (odd depth).
This maximizes the number of red nodes, which do not count toward black height,
resulting in the smallest possible black height of approximately log2(n)/2.
For n=1000000, the black height is approximately 10.
*/