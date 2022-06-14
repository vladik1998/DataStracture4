import java.util.LinkedList;
import java.util.List;

public class BacktrackingAVL extends AVLTree {
    // For clarity only, this is the default ctor created implicitly.
    public BacktrackingAVL() {
        super();
    }

    //You are to implement the function Backtrack.
    public void Backtrack() {
        if (!backtrackInsert.isEmpty()) {
            if (backtrackType.peekLast().equals("LEFT_LEFT")) {
                backtrackType.removeLast();
                Node node = backtrackInsert.removeLast();
                Node rotated = backtrackRotated.removeLast();
                Node parentRotated = rotated.parent;
                if (parentRotated.parent != null) {
                    if (parentRotated.parent.right == parentRotated) {
                        parentRotated.parent.right = rotated;
                        rotated.parent = parentRotated.parent;
                    } else {
                        parentRotated.parent.left = rotated;
                        rotated.parent = parentRotated.parent;
                    }
                } else {
                    root = rotated;
                    rotated.parent = null;
                }
                parentRotated.right = rotated.left;
                if (rotated.left != null) {
                    rotated.left.parent = parentRotated;
                }
                rotated.left = parentRotated;
                parentRotated.parent = rotated;
                Node parent = null;
                if (backtrackValue.removeLast() == 1) {
                    parent = node.parent;
                    if (parent.left == node) {
                        node.parent.left = null;
                    }
                    else {
                        node.parent.right = null;
                    }
                    node.parent = null;
                }
                if (parent == null) {
                    parent = backtrackInsert.peekLast();
                }
                while (parent != null) {
                    parent.updateHeight();
                    parent.updateSize();
                    parent = parent.parent;
                }
            }
            else {
                if (backtrackType.peekLast().equals("RIGHT_RIGHT")) {
                    backtrackType.removeLast();
                    Node node = backtrackInsert.removeLast();
                    Node rotated = backtrackRotated.removeLast();
                    Node parentRotated = rotated.parent;
                    if (parentRotated.parent != null) {
                        if (parentRotated.parent.right == parentRotated) {
                            parentRotated.parent.right = rotated;
                            rotated.parent = parentRotated.parent;
                        } else {
                            parentRotated.parent.left = rotated;
                            rotated.parent = parentRotated.parent;
                        }
                    } else {
                        root = rotated;
                        rotated.parent = null;
                    }
                    parentRotated.left = rotated.right;
                    if (rotated.right != null) {
                        rotated.right.parent = parentRotated;
                    }
                    rotated.right = parentRotated;
                    parentRotated.parent = rotated;
                    Node parent = null;
                    if (backtrackValue.removeLast() == 1) {
                        parent = node.parent;
                        if (parent.right == node) {
                            node.parent.right = null;
                        }
                        else {
                            node.parent.left = null;
                        }
                        node.parent = null;
                    }
                    if (parent == null)
                        parent = backtrackInsert.peekLast();
                    while (parent != null) {
                        parent.updateHeight();
                        parent.updateSize();
                        parent = parent.parent;
                    }
                } else {
                    if (backtrackType.peekLast().equals("NO_IMBALANCE")) {
                        backtrackValue.removeLast();
                        backtrackType.removeLast();
                        Node node = backtrackInsert.removeLast();
                        if (node.parent != null) {
                            Node parent = node.parent;
                            if (node.parent.left == node) {
                                node.parent.left = null;
                            } else {
                                node.parent.right = null;
                            }
                            node.parent = null;
                            while (parent != null) {
                                parent.updateHeight();
                                parent.updateSize();
                                parent = parent.parent;
                            }
                        } else {
                            root = null;
                        }
                    }
                    else {
                        if (backtrackType.peekLast().equals("LEFT_RIGHT") | backtrackType.peekLast().equals("RIGHT_LEFT")) {
                            backtrackType.removeLast();
                            Backtrack();
                            Backtrack();
                        }
                    }
                }
            }
        }
    }

    //Change the list returned to a list of integers answering the requirements
    public static List<Integer> AVLTreeBacktrackingCounterExample() {
        List<Integer> inserts = new LinkedList<>();
        inserts.add(1);
        inserts.add(2);
        inserts.add(3);
        inserts.add(4);
        inserts.add(5);
        inserts.add(6);
        inserts.add(7);
        inserts.add(8);
        inserts.add(9);
        inserts.add(10);
        inserts.add(11);
        inserts.add(12);
        return inserts;
    }

    public int Select(int index) {
        if (root == null)
            return 0;
        Node node = root;
        int result = node.value;
        int numberI = node.leftSize + 1;
        while (numberI != index) {
            if (numberI > index) {
                numberI -= (1 + node.left.rightSize);
                node = node.left;
            }
            else {
                numberI += (1 + node.right.leftSize);
                node = node.right;
            }
            result = node.value;
        }
        return result;
    }

    public int Rank(int value) {
        Node node = root;
        int result = 0;
        while (node != null) {
            if (node.value == value) {
                result += node.leftSize;
                node = null;
            }
            else {
                if (node.value > value) {
                    node = node.left;
                }
                else {
                    result += node.leftSize + 1;
                    node = node.right;
                }
            }
        }
        return result;
    }
}
