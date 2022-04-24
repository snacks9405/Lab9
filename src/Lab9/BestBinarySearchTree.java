package Lab9;

import java.util.ArrayList;
import net.datastructures.LinkedBinaryTree;
import net.datastructures.Position;
import dsaj.trees.TraversalExamples;

/**
 * probably the greatest example of a binary search tree that's ever existed
 */
public class BestBinarySearchTree<E extends Comparable<E>> extends LinkedBinaryTree<E> {
    /**
     * This convenience constructor will pre-populate the tree using the array of
     * elements, inserting each one in turn, correctly, into the tree
     * 
     * @param elements
     */
    public BestBinarySearchTree(E[] elements) {
        this(elements, true);
    }//BestBinarySearchTree constructor

    /**
     * This convenience constructor will pre-populate the tree using the array of
     * elements, inserting each one in turn -- correctly, with insert() if doItRight
     * is true, or incorrectly, with brokenInsert() if it is false
     * 
     * @param elements
     * @param doItRight
     */
    public BestBinarySearchTree(E[] elements, boolean doItRight) {
        for (E dataToTree : elements) { //iterate the elements and build the tree...
            if (doItRight) {
                insert(dataToTree); //the right way or...
            } else {
                brokenInsert(dataToTree); //the naughty way.
            }
        }
    }//BestBinarySearchTree constructor

    /**
     * inserts element at its correct Position, and returns that Position. If the
     * element already exists, throw an illegal state exception.
     * 
     * @param element
     * @return
     */
    public Position<E> insert(E element) throws IllegalStateException {
        if (contains(element) != null) { //if the item exists in the tree, throw exception
            throw new IllegalStateException();
        }
        if (root == null) {
            return addRoot(element); //give it a root if it has none
        }
        Position<E> walk = root; //walker will travel to the right placement
        while (walk != null) {
            if (element.compareTo(walk.getElement()) < 0) { //if element is less than walk...
                if (left(walk) == null) {
                    return addLeft(walk, element); //make it the left child or...
                } else {
                    walk = left(walk); //move on to the left child.
                }
            } else {
                if (right(walk) == null) { //if element is greater than walk...
                    return addRight(walk, element); //make it the right child or...
                } else {
                    walk = right(walk); //move on to the right child.
                }
            }
        }
        return null;
    }//insert method

    /**
     * inserts element to the left of the leftmost node in the tree, ignoring its
     * actual value. This breaks the binary search tree property, hence the name.
     * 
     * @param element
     * @return
     */
    public Position<E> brokenInsert(E element) {
        if (root() == null) { //give it a root
            return addRoot(element);
        } else {
            Position<E> walk = root();
            while (left(walk) != null) { //element is placed as the furthest left child.
                walk = left(walk);
            }
            return addLeft(walk, element);
        }
    }//brokenInsert method

    /**
     * returns true if the tree is a binary search tree, false otherwise.
     * 
     * @return
     */
    public boolean isBinarySearchTree() {
        return isBinarySearchTree(root); //start recursive search at the root
    }//isBinarySearchTree method

    /**
     * returns true if the tree is a binary search tree, false otherwise.
     * recursive implementation
     * @param curPos 
     * @return
     */
    public boolean isBinarySearchTree(Position<E> curPos) {
        Position<E> leftChild = left(curPos); //get left child or null
        Position<E> rightChild = right(curPos); //get right child or null
        if (leftChild != null) {
            if (curPos.getElement().compareTo(leftChild.getElement()) < 0) { //if an element is out of place...
                return false;
            }
            return (isBinarySearchTree(leftChild));
        } else if (rightChild != null) {
            if (curPos.getElement().compareTo(rightChild.getElement()) > 0) { //return false 
                return false;
            }
            return (isBinarySearchTree(rightChild));
        }
        return true; //if everything checks out, it's definitely a binary search tree.
    }//isBinarySearchTree method

    /**
     * returns the position of the element, null if it is not present
     * checks root cases, then sends it to a further search method. the
     * type is depended on whether the tree is a binary search tree or not.
     * @param element
     * @return
     */
    public Position<E> contains(E element) {
        if (root == null) { //cursory examination will just check if there's even a root...
            return null;
        }
        if (root.getElement().compareTo(element) == 0) { //then if the root matches the element.
            return root;
        }
        return (isBinarySearchTree() ? contains(element, root) : badTreeContains(element));
    }//contains method

    /**
     * recursively searches a verified binary search tree by iterating through each position
     * and its children until all spots are checked or the element is found
     * 
     * @param element
     * @param curPos
     * @return
     */
    public Position<E> contains(E element, Position<E> curPos) {
        if (curPos.getElement().compareTo(element) == 0) { //if curPos is our element, return it
            return curPos;
        } else if (element.compareTo(curPos.getElement()) < 0) {
            if (left(curPos) == null) { //if element is less than curPos element, check it's child or return null
                return null;
            }
            return contains(element, left(curPos));
        } else {
            if (right(curPos) == null) { //if element is greater than curPos element, check it's child or return null
                return null;
            }
            return contains(element, right(curPos));
        }
    }//contains method

    /**
     * searches through a weird left only array by reapeatdly grabbing the 
     * left child
     * @param element
     * @return
     */
    public Position<E> badTreeContains(E element) {
        Position<E> walk = root; 
        while (walk != null) {
            if (element.compareTo(walk.getElement()) == 0) { //if it finds a match, return it
                return walk;
            }
            walk = left(walk);
        }
        return null; //or return null
    }//badTreeContains method

    /**
     * This will print the tree, leveraging method(s) in
     * dsaj.trees.TraversalExamples.
     */
    public void printTree() {
        TraversalExamples.printPreorderLabeled(this, root(), new ArrayList<Integer>());
    }//printTree method
}//BestBinarySearchTree class