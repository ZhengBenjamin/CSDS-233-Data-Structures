/**
 * Has properties of both stack and queue. 
 * Implements using an array 
 * @author Benjamin Zheng bxz346
 */

public class SuperDeque<E> {
    
    private E[] dq; //Array storing data 
    private int front; //Index represneting front/top 
    private int back;  //Index representing back/bottom
    private int currentSize; //Represents the current size of the array 

    private static final int DEFAULT_CAP = 1; //Initial size 

    public SuperDeque() {
        this.dq = (E[]) new Object[DEFAULT_CAP];
        this.front = 0;
        this.back = 0;
        this.currentSize = 0;
    }

    /**
     * Acts like stack; Adds the element to the top of the stack 
     * @param element Element being pushed to the top 
     */
    public void push(E element) {
        if (currentSize == dq.length) { //Checks for space, doubles if not enough 
            doubleSize(); 
        }
        
        if (front == 0) { //Decreases index by one 
            front = dq.length - 1; 
        } else {
            front--; 
        }

        dq[front] = element; 
        currentSize++; 
    }

    /**
     * Acts like stack; Removes element from top of stack 
     * @return element that's returned 
     */
    public E pop() {
        if (isEmpty()) {
            return null; 
        }

        E element = dq[front];
        dq[front] = null;
        front = (front + 1) % dq.length;  
        currentSize--;
        return element; 
    }

    /**
     * Checks the top of the stack 
     * @return the element; does not remove the element 
     */
    public E peek() {
        return dq[front];
    }

    /**
     * Adds an element to the back of the SuperDeque 
     * @param element to add to the queue 
     */
    public void enqueue(E element) {
        if (currentSize == dq.length) {
            doubleSize();
        }
        
        back = (back + 1) % dq.length; 
        dq[back] = element; 
        currentSize++; 
    }

    /**
     * Acts like a queue; removes element at the front of SuperDeque 
     * @return Element removed 
     */
    public E dequeue() {
        if (isEmpty()) {
            return null; 
        }

        E element = dq[front]; 
        dq[front] = null;
        front = (front + 1) % dq.length;
        currentSize--;
        return element; 
    }

    /**
     * Checks if the SuperDeque is empty 
     * @return is empty 
     */
    public boolean isEmpty() {
        if (currentSize == 0) {
            return true;
        } 
        return false; 
    }

    /**
     * Doubles the size of the array :::Make this private when done testing 
     */
    private void doubleSize() {
        @SuppressWarnings("unchecked")
        E[] newDq = (E[]) new Object[dq.length * 2]; //Temp array to store elements 
    
        int i = front;
        int newIndex = 0;
        while (i != back) { //Copies elments to temp array 
            newDq[newIndex++] = dq[i];
            i = (i + 1) % dq.length;
        }
        newDq[newIndex] = dq[back];
    
        front = 0;
        back = newIndex;
    
        dq = newDq;
    }
    

    /**
     * Prints deque as a string "Element1, Element2, Element3, ..."
     */
    public String toString() {
        StringBuilder string = new StringBuilder();
        int count = 0; 
        int index = front; 

        while (count < getCurrentSize()) { //Iterates through the array and puts all non null elments into the StringBuilder 
            if (dq[index] != null) {
                string.append(dq[index]);
                if (count < currentSize - 1) {
                    string.append(", ");
                }
                count++;
            }
            index = (index + 1) % dq.length; 
        }

        return string.toString(); 
    }

    /**
     * Gets the current size of intenral array 
     * @return size 
     */
    private int getCurrentSize() {
        return currentSize; 
    }
}
