import java.util.*;

public class DualImplementationBag<T> {
    private boolean useArrayList; //Choose between ArrayList and LinkedList
    public List<T> internalList; //List to store bag elements 
    private int items; //Number of items in bag 

    /**
     * Constructor for DualImplementationBag 
     * @param useArrayList Sets the implementation of the bag 
     */
    public DualImplementationBag(boolean useArrayList) {
        if (useArrayList == true) {
            internalList = new ArrayList<T>();
        } else {
            internalList = new LinkedList<T>(); 
        }
        this.useArrayList = useArrayList;
        this.items = 0;
    }

    /**
     * Changes the implementaiton of the bag from ArrayList to LinkedList and vise versa
     * @param useArrayList Defines which implementation to use 
     */
    public void setUseArrayList(boolean useArrayList) {
        if (this.useArrayList != useArrayList) { //Checks if List is the wrong type 
            
            this.useArrayList = useArrayList; 
            
            if (isEmpty()) {
                if (this.useArrayList) { //Changes linked list to array list 
                    internalList = new ArrayList<>();
                } else { //Changes array list to linked list 
                    internalList = new LinkedList<>();
                }
            } else {
                if (this.useArrayList) { //Copies internal list to new temp array list and copies back 
                    List<T> tempList = new ArrayList<>(internalList); 
                    internalList = tempList; 
                } else { //Copies internal list to new temp linked list and copies back 
                    List<T> tempList = new LinkedList<>(internalList);
                    internalList = tempList; 
                }
            }
        }
    }

    /**
     * Adds an element into the bag 
     * @param element Element to be added 
     * @return Returns true if element is sucessfully added 
     */
    public boolean add(T element) {
        internalList.add(element);
        items++;
        return true; 
    }

    /**
     * Removes an element from a bag 
     * @param element Element being removed 
     * @return Returns true if sucessfully removed 
     */
    public boolean remove(T element) {
        if (contains(element)) {
            internalList.remove(element); 
            items--; 
            return true;
        }
        return false; 
    }

    /** 
     * Checks if the bag contains a specific element
     * @param element Elmenet being checked 
     * @return Returns true if sucesfully removed 
     */
    public boolean contains(T element) {
        for (int i = 0; i < internalList.size(); i++) {
            if (get(i).equals(element)) {
                return true; 
            }
        }
        return false; 
     }

     /**
      * Checks if the bag is empty
      * @return Returns if bag is empty 
      */
    public boolean isEmpty() {
        if (items == 0) {
            return true;
        }
        return false; 
    }

    /**
     * Gets the number of items in the bag 
     * @return Number of items in the bag 
     */
    public int size() {
        return items; 
    }

    /**
     * Counts the number of times a specific element appears in the bag 
     * @param element Element being searched 
     * @return The number of times the element appeared in the bag 
     */
    public int getFrequencyOf(T element) {
        int count = 0;
        for (int i = 0; i < size(); i++) {
            if (get(i).equals(element)) {
                count++;
            }
        }
        return count; 
    }

    /**
     * Gets the element in a specific index of the bag (0 indexed)
     * @param index Index of element
     * @return Returns the element in index. Returns null if there is no element or index out of bounds 
     */
    public T get(int index) {
        if (index - 1 < size() || index > 0) {
            return internalList.get(index);
        }
        return null;
    }

    /**
     * Helper method to determine if is array list 
     * @return Returns if array list 
     */
    public boolean isArrayList() {
        return useArrayList;
    }
    
}
