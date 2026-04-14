//
// HX-2026-04-09: 50 points
// A partial implementation of
// randomized doubly linked binary search tree
// 20 points for insert and 30 points for remove
//
public class Assign09_01 {
    Node root = null;
    public class Node {
        int key; // key stored in the node
        int size; // size of the tree rooted as the node
        Node parent; // parent of the node
        Node lchild; // left-child of the node
        Node rchild; // right-child of the node
    }

    public boolean insert(int key) {
        // HX-2026-04-09: 20 points
        // If key is in the tree stored at [root],
        // [insert] does nothing and just returns false.
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
        node.lchild = null;
        node.rchild = null;
        if (parent == null) {
            root = node;
        } else if (key < parent.key) {
            parent.lchild = node;
        } else {
            parent.rchild = node;
        }
        // update sizes up the tree
        Node p = parent;
        while (p != null) {
            p.size += 1;
            p = p.parent;
        }
        return true;
    }

    public boolean remove(int key) {
        // HX-2026-04-09: 20 points
        // If key is in the tree stored at [root],
        // [remove] removes the key and the new tree
        // obtained is still a binary search tree and
        // [remove] returns true to indicate the removal
        // is done.
        // If key is not in the tree stored at [root],
        // [remove] does nothing and returns false to
        // indicate that no removal of the key k is done.
        Node curr = root;
        while (curr != null) {
            if (key == curr.key) break;
            if (key < curr.key) curr = curr.lchild;
            else curr = curr.rchild;
        }
        if (curr == null) return false;

        if (curr.lchild == null && curr.rchild == null) {
            // Case 1: leaf node
            replaceNode(curr, null);
        } else if (curr.lchild == null) {
            // Case 2: only right child
            replaceNode(curr, curr.rchild);
        } else if (curr.rchild == null) {
            // Case 3: only left child
            replaceNode(curr, curr.lchild);
        } else {
            // Case 4: two children - find in-order successor
            Node successor = curr.rchild;
            while (successor.lchild != null) {
                successor = successor.lchild;
            }
            // update sizes before moving successor
            Node p = successor.parent;
            while (p != null && p != curr) {
                p.size -= 1;
                p = p.parent;
            }
            curr.key = successor.key;
            replaceNode(successor, successor.rchild);
            // update curr size
            curr.size -= 1;
            Node p2 = curr.parent;
            while (p2 != null) {
                p2.size -= 1;
                p2 = p2.parent;
            }
            return true;
        }
        // update sizes up the tree
        Node p = curr.parent;
        while (p != null) {
            p.size -= 1;
            p = p.parent;
        }
        return true;
    }

    private void replaceNode(Node node, Node replacement) {
        if (node.parent == null) {
            root = replacement;
        } else if (node == node.parent.lchild) {
            node.parent.lchild = replacement;
        } else {
            node.parent.rchild = replacement;
        }
        if (replacement != null) {
            replacement.parent = node.parent;
        }
    }

    public static void main(String[] args) {
        // Please add minimal testing code for insert()
        // Please add minimal testing code for remove()
        Assign09_01 tree = new Assign09_01();

        // Test insert
        System.out.println("insert 5: " + tree.insert(5)); // true
        System.out.println("insert 3: " + tree.insert(3)); // true
        System.out.println("insert 7: " + tree.insert(7)); // true
        System.out.println("insert 3: " + tree.insert(3)); // false (duplicate)
        System.out.println("insert 1: " + tree.insert(1)); // true
        System.out.println("insert 4: " + tree.insert(4)); // true

        // Test remove
        System.out.println("remove 3: " + tree.remove(3)); // true
        System.out.println("remove 3: " + tree.remove(3)); // false (already removed)
        System.out.println("remove 5: " + tree.remove(5)); // true
        System.out.println("remove 99: " + tree.remove(99)); // false (not in tree)

        return /*void*/;
    }
}
