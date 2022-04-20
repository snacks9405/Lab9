import Structures.LinkedBinaryTree;
import Structures.Position;

public class BestBinarySearchTree<E extends Comparable<E>> extends LinkedBinaryTree<E> {

    /**
     * This convenience constructor will pre-populate the tree using the array of
     * elements, inserting each one in turn, correctly, into the tree
     * 
     * @param elements
     */
    public BestBinarySearchTree(E[] elements) {

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

    }

    /**
     * inserts element at its correct Position, and returns that Position. If the
     * element already exists, throw an illegal state exception.
     * 
     * @param element
     * @return
     */
    public Position<E> insert(E element) {
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
        return null;
    }

    /**
     * returns true if the tree is a binary search tree, false otherwise
     * 
     * @return
     */
    public boolean isBinarySearchTree() {
        return false;
    }

    /**
     * returns the position of the element, null if it is not present
     * 
     * @param element
     * @return
     */
    public Position<E> contains(E element) {
        return null;
    }

    /**
     * This will print the tree, leveraging method(s) in
     * dsaj.trees.TraversalExamples.
     */
    public void printTree() {

    }

}
