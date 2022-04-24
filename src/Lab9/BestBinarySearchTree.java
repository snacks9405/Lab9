package Lab9;

import java.util.ArrayList;
import net.datastructures.LinkedBinaryTree;
import net.datastructures.Position;
import dsaj.trees.TraversalExamples;

public class BestBinarySearchTree<E extends Comparable<E>> extends LinkedBinaryTree<E> {
    /**
     * This convenience constructor will pre-populate the tree using the array of
     * elements, inserting each one in turn, correctly, into the tree
     * 
     * @param elements
     */
    public BestBinarySearchTree(E[] elements) {
        for (E dataToTree : elements) {
            insert(dataToTree);
        }
    }

    /**
     * This convenience constructor will pre-populate the tree using the array of
     * elements, inserting each one in turn -- correctly, with insert() if doItRight
     * is true, or incorrectly, with brokenInsert() if it is false
     * 
     * @param elements
     * @param doItRight
     */
    public BestBinarySearchTree(E[] elements, boolean doItRight) {
        for (E dataToTree : elements) {
            if (doItRight) {
                insert(dataToTree);
            } else {
                brokenInsert(dataToTree);
            }
        }
    }

    /**
     * inserts element at its correct Position, and returns that Position. If the
     * element already exists, throw an illegal state exception.
     * 
     * @param element
     * @return
     */
    public Position<E> insert(E element) throws IllegalStateException {
        if (contains(element) != null) {
            throw new IllegalStateException();
        }
        if (root == null) {
            return addRoot(element);
        }
        Position<E> walk = root;
        while (walk != null) {
            if (element.compareTo(walk.getElement()) < 0) {
                if (left(walk) == null) {
                    return addLeft(walk, element);
                } else {
                    walk = left(walk);
                }
            } else {
                if (right(walk) == null) {
                    return addRight(walk, element);
                } else {
                    walk = right(walk);
                }
            }

        }
        return null;
    }

    /**
     * inserts element to the left of the leftmost node in the tree, ignoring its
     * actual value. This breaks the binary search tree property, hence the name.
     * 
     * @param element
     * @return
     */
    public Position<E> brokenInsert(E element) {
        if (root() == null) {
            return addRoot(element);
        } else {
            Position<E> walk = root();
            while (left(walk) != null) {
                walk = left(walk);
            }
            return addLeft(walk, element);
        }
    }

    /**
     * returns true if the tree is a binary search tree, false otherwise
     * 
     * @return
     */
    public boolean isBinarySearchTree() {
        return isBinarySearchTree(root);
    }

    public boolean isBinarySearchTree(Position<E> curPos) {
        Position<E> leftChild = left(curPos);
        Position<E> rightChild = right(curPos);
        if (leftChild != null) {
            if (curPos.getElement().compareTo(leftChild.getElement()) < 0) {
                return false;
            }
            return (isBinarySearchTree(leftChild));
        } else if (rightChild != null) {
            if (curPos.getElement().compareTo(rightChild.getElement()) > 0) {
                return false;
            }
            return (isBinarySearchTree(rightChild));
        }
        return true;
    }

    /**
     * returns the position of the element, null if it is not present
     * 
     * @param element
     * @return
     */
    public Position<E> contains(E element) {
        if (root == null) {
            return null;
        }
        if (root.getElement().compareTo(element) == 0) {
            return root;
        }
        return (isBinarySearchTree() ? contains(element, root) : badTreeContains(element));

    }

    public Position<E> contains(E element, Position<E> curPos) {
        if (curPos.getElement().compareTo(element) == 0) {
            return curPos;
        } else if (element.compareTo(curPos.getElement()) < 0) {
            if (left(curPos) == null) {
                return null;
            }
            return contains(element, left(curPos));
        } else {
            if (right(curPos) == null) {
                return null;
            }
            return contains(element, right(curPos));
        }
    }

    /**
     * 
     * @param element
     * @return
     */
    public Position<E> badTreeContains(E element) {
        Position<E> walk = root;
        while (walk != null) {
            if (element.compareTo(walk.getElement()) == 0) {
                return walk;
            }
            walk = left(walk);
        }
        return null;
    }

    /**
     * This will print the tree, leveraging method(s) in
     * dsaj.trees.TraversalExamples.
     */
    public void printTree() {
        TraversalExamples.printPreorderLabeled(this, root(), new ArrayList<Integer>());
    }

}
