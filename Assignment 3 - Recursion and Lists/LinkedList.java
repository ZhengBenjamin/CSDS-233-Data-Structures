import java.lang.IllegalArgumentException;

/**
 * LinkedList class that contains methods to add, remove, and manipulate elements in a linked list
 * @author Benjamin Zheng bxz346
 */
public class LinkedList {

    private Node topNode;

    /**
     * Adds element n to the back of the list
     */
    public void add(int n) {
        if (topNode == null) {
            topNode = new Node(n);
            return;
        }

        Node nodePointer = topNode; 

        while (nodePointer.hasNext()) {
            nodePointer = nodePointer.next(); 
        }

        nodePointer.setNext(new Node(n));
    }

    /**
     * Adds element n to the specified index in the list 
     * @param n Element being added 
     * @param index Index to add to 
     */
    public void add(int n, int index) {
        if (index < 0) {
            throw new IllegalArgumentException(); 
        } else if (index == 0) {
            topNode = new Node(n, topNode);
            return;
        } else if (index + 1 >= getSize()) {
            index = getSize() - 1; 
        } 

        Node nodePointer = topNode;

        for (int i = 0; i < index - 1; i++) {
            nodePointer = nodePointer.next(); 
        }

        nodePointer.setNext(new Node(n, nodePointer.next())); 
    }

    /**
     * Finds an element in the list 
     * @param n Element being found
     * @return Index of the element 
     */
    public int indexof(int n) {
        if (topNode == null) {
            return -1; 
        } else if (topNode.getElement() == n) {
            return 0; 
        }

        int index = 1; 
        Node nodePointer = topNode; 

        while (nodePointer.hasNext()) {
            nodePointer = nodePointer.next(); 
            if (nodePointer.getElement() == n) {
                return index; 
            }
            index++; 
        }
        
        return -1; 
    }


    /**
     * Removes the element at a specific index 
     * @param index Index of element being removed 
     * @return The element removed 
     */
    public int remove(int index) {
        if (index < 0 || getSize() == 0) { //Checks if index is negative or list is empty
            throw new IllegalArgumentException(); 
        } else if (getSize() == 1) { //Checks if list has one element
            int element = topNode.getElement();
            topNode = null; 
            return element;
        } else if (index == 0) { //Checks if index is at the beginning of the list
            int element = topNode.getElement();
            topNode = topNode.next(); 
            return element; 
        } else if (index + 1 >= getSize()) { //Checks if index is out of bounds
            index = getSize() - 1; 
        }

        Node nodePointer = topNode;
        int element; 

        for (int i = 0; i < index - 1; i++) {
            nodePointer = nodePointer.next(); 
        }

        element = nodePointer.getElement();
        nodePointer.setNext(nodePointer.next().next()); 
        
        return element; 
    }

    /**
     * Removes the first instance of a value
     * @param n Element to be removed
     */
    public void removeValue(int n) {

        if (topNode == null) {
            return;
        }

        Node nodePointer = topNode; 
        int index = 0; 

        while (nodePointer.hasNext()) { //Iterates through the list and removes the first instance of n 
            if (nodePointer.getElement() == n) {
                remove(index);
                return;
            }
            index++; 
            nodePointer = nodePointer.next();
        }

        if (nodePointer.getElement() == n) { //Checks the last element
            remove(index);
        }

    }

    /**
     * Removes all instances of element n 
     * @param n Element being removed 
     */
    public void removeall(int n) {
        if (topNode == null) {
            return; 
        }

        Node nodePointer = topNode; 
        int index = 0; 

        while (nodePointer.hasNext()) { //Iterates through the list and removes all instances of n
            if (nodePointer.getElement() == n) {
                remove(index);
            } else {
                index++; 
            }
            nodePointer = nodePointer.next();
        }

        if (nodePointer.getElement() == n) { //Checks the last element
            remove(0);
        }
    }

    /**
     * Calculates the mean of all elements in the list
     * @return Mean value 
     */
    public double mean() {

        if (topNode == null) {
            return 0; 
        }

        int sum = 0;
        Node nodePointer = topNode; 

        while (nodePointer.hasNext()) { //Iterates through the list and adds all elements
            sum += nodePointer.getElement();
            nodePointer = nodePointer.next();
        }

        sum += nodePointer.getElement(); //Adds the last element

        return sum / getSize(); 
    }

    /**
     * Calculates the variance of all the elemnets of the list 
     * @return Variance value 
     */
    public double variance() {
        if (topNode == null || getSize() == 1) {
            return 0; 
        } 

        double sum = 0;
        double mean = mean();
        Node nodePointer = topNode; 

        while (nodePointer.next() != null) { //Iterates through the list and adds the squared difference of each element from the mean
            sum += Math.pow((nodePointer.getElement() - mean), 2); 
            nodePointer = nodePointer.next(); 
        }

        sum += Math.pow((nodePointer.getElement() - mean), 2); //Adds the last element

        return sum / (getSize() - 1);
    }

    /**
     * Returns a new Array with all elements of the original list that are within the lwoer and upper bounds
     * @param lower Lower bound
     * @param upper Upper bound
     * @return LinkedList with all elments in between the bounds 
     */
    public LinkedList sublist(int lower, int upper) {
        if (topNode == null) {
            return new LinkedList(); 
        } else if (lower > upper) {
            throw new IllegalArgumentException(); 
        }

        LinkedList newList = new LinkedList(); 
        Node nodePointer = topNode;

        while (nodePointer.next() != null) {
            if (nodePointer.getElement() >= lower && nodePointer.getElement() <= upper) {
                newList.add(nodePointer.getElement());
            }
            nodePointer = nodePointer.next();
        }

        if (nodePointer.getElement() >= lower && nodePointer.getElement() <= upper) {
            newList.add(nodePointer.getElement());
        }

        return newList; 
    }

    /**
     * Returns a new LinkedList with all elements of the original list that were within 3 standard deviation of mean
     * @return LinkedList with elements under 3 standard deviations of mean 
     */
    public LinkedList removeNoise() {
        double mean = mean(); 
        double standardDeviation = (int) Math.sqrt(variance()) * 3;

        return sublist((int) Math.floor(mean - standardDeviation), (int) Math.ceil(mean + standardDeviation));
    }

    /**
     * Returns the size of the list 
     * @return Size of the list 
     */
    public int getSize() {
        if (topNode == null) {
            return 0; 
        }

        int size = 1; 
        Node nodePointer = topNode; 

        while (nodePointer.hasNext()) {
            size++;
            nodePointer = nodePointer.next(); 
        }

        return size;  
    }

    /**
     * Returns the element at a specific index
     * @param index Index of element
     * @return Element at index
     */
    public int get(int index) {
        if (index < 0) {
            throw new IllegalArgumentException();
        } 

        Node nodePointer = topNode;
        
        for (int i = 0; i < index; i++) {
            nodePointer = nodePointer.next(); 
        }

        return nodePointer.getElement();
    }

    /**
     * Helper method to print the list
     */
    /* 
    public void printList() {
        Node nodePointer = topNode; 

        while (nodePointer.hasNext()) {
            System.out.println(nodePointer.getElement());
            nodePointer = nodePointer.next();
        }

        System.out.println(nodePointer.getElement());
    }
    */
}
