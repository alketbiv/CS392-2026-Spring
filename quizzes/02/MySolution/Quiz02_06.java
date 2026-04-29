//
// HX-2026-04-28: 50 points
// A partial implementation of
// randomized doubly linked binary search tree
// 30 points for reroot and 20 points for insert
//
public class Quiz02_06 {
    Node root = null;
    public class Node {
        int key; // key stored in the node
        int size; // size of the tree rooted as the node
        Node parent; // parent of the node
        Node lchild; // left-child of the node
        Node rchild; // right-child of the node
    }

    private int treeSize(Node n) {
        return (n == null) ? 0 : n.size;
    }

    private void updateSize(Node n) {
        if (n != null)
            n.size = 1 + treeSize(n.lchild) + treeSize(n.rchild);
    }

    // Right rotation at node y
    private void rotateRight(Node y) {
        Node x = y.lchild;
        Node p = y.parent;

        y.lchild = x.rchild;
        if (x.rchild != null) x.rchild.parent = y;

        x.rchild = y;
        y.parent = x;
        x.parent = p;

        if (p == null) root = x;
        else if (p.lchild == y) p.lchild = x;
        else p.rchild = x;

        updateSize(y);
        updateSize(x);
    }

    // Left rotation at node x
    private void rotateLeft(Node x) {
        Node y = x.rchild;
        Node p = x.parent;

        x.rchild = y.lchild;
        if (y.lchild != null) y.lchild.parent = x;

        y.lchild = x;
        x.parent = y;
        y.parent = p;

        if (p == null) root = y;
        else if (p.lchild == x) p.lchild = y;
        else p.rchild = y;

        updateSize(x);
        updateSize(y);
    }

    // Move node up to root using rotations
    private void splay(Node n) {
        while (n.parent != null) {
            Node p = n.parent;
            if (p.parent == null) {
                if (p.lchild == n) rotateRight(p);
                else rotateLeft(p);
            } else {
                Node g = p.parent;
                if (g.lchild == p && p.lchild == n) {
                    rotateRight(g); rotateRight(p);
                } else if (g.rchild == p && p.rchild == n) {
                    rotateLeft(g); rotateLeft(p);
                } else if (g.lchild == p && p.rchild == n) {
                    rotateLeft(p); rotateRight(g);
                } else {
                    rotateRight(p); rotateLeft(g);
                }
            }
        }
    }

    // Get node at rank k (0-indexed)
    private Node getNodeAtRank(int k) {
        Node cur = root;
        while (cur != null) {
            int leftSize = treeSize(cur.lchild);
            if (k == leftSize) return cur;
            else if (k < leftSize) cur = cur.lchild;
            else { k -= leftSize + 1; cur = cur.rchild; }
        }
        return null;
    }

    public void reroot() {
        // HX-2025-11-20: 30 points
        // [reroot] picks a node RANDOMLY and
        // uses rotations to turn this picked node
        // into the root of a new binary search tree
        if (root == null) return;
        int n = treeSize(root);
        int rank = (int)(Math.random() * n);
        Node picked = getNodeAtRank(rank);
        if (picked != null) splay(picked);
    }

    public boolean insert(int key) {
        // HX-2025-11-20: 20 points
        // If key is in the tree stored at [root],
        // [insert] does no nothing and just returns false
        // If key is not in the tree stored at [root],
        // the key is inserted as a leaf node and the new
        // tree is still a binary search tree and [insert]
        // returns true (to indicate insertion is done).
        Node parent = null;
        Node curr = root;
        while (curr != null) {
            if (key == curr.key) return false;
            parent = curr;
            if (key < curr.key) curr = curr.lchild;
            else curr = curr.rchild;
        }
        Node node = new Node();
        node.key = key;
        node.size = 1;
        node.parent = parent;
        if (parent == null) {
            root = node;
        } else if (key < parent.key) {
            parent.lchild = node;
        } else {
            parent.rchild = node;
        }
        Node p = parent;
        while (p != null) {
            p.size += 1;
            p = p.parent;
        }
        return true;
    }

    public static void main(String[] args) {
        // Please add minimal testing code for reroot()
        // Please add minimal testing code for insert()
        Quiz02_06 tree = new Quiz02_06();

        // Test insert
        System.out.println("insert 5: " + tree.insert(5)); // true
        System.out.println("insert 3: " + tree.insert(3)); // true
        System.out.println("insert 7: " + tree.insert(7)); // true
        System.out.println("insert 5: " + tree.insert(5)); // false (duplicate)
        System.out.println("size: " + tree.root.size);     // 3

        // Test reroot
        int oldRootKey = tree.root.key;
        tree.reroot();
        System.out.println("reroot done, new root key: " + tree.root.key);
        System.out.println("size after reroot: " + tree.root.size); // still 3

        return /*void*/;
    }
}