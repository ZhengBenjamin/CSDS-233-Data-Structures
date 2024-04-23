/**
 * Node class for the linked list
 * @author Benjamin Zheng bxz346
 */

public class Node {
    private int n; //Element of the node
    private Node next; //Pointer to the next node

    /**
     * Constructor for the Node class
     * @param n Element of the node
     */
    public Node(int n) {
        this.n = n; 
        this.next = null; 
    }

    /**
     * Constructor for the Node class
     * @param n Element of the node
     * @param next Pointer to the next node
     */
    public Node(int n, Node next)  {
        this.n = n;
        this.next = next; 
    }

    /**
     * Returns the element of the node
     * @return Element of the node
     */
    public int getElement() {
        return n; 
    }

    /**
     * Checks if the node has a next node
     * @return True if there is a next node, false otherwise
     */
    public boolean hasNext() {
        if (next != null) {
            return true; 
        } else {
            return false; 
        }
    }

    /**
     * Returns the next node
     * @return Next node
     */
    public Node next() {
        return next; 
    }

    /**
     * Sets the next node
     * @param next Next node
     */
    public void setNext(Node next) {
        this.next = next; 
    }

}