import java.lang.IllegalArgumentException;

/**
 * ArrayList class that contains methods to manipulate an array of integers 
 * @author Benjamin Zheng bxz346
 */
public class ArrayList {

    private int[] internalArray = new int[0];

    /**
     * Adds integer n to the end of the list 
     * @param n integer being added 
     */
    public void add(int n) {
        feedArray();
        internalArray[internalArray.length - 1] = n;  
    }

    /**
     * Adds integer n to the specified index of the list 
     * @param n integer being added 
     * @param index specified index 
     */
    public void add(int n, int index) {
        
        if (index < 0) {
            throw new IllegalArgumentException("Index cannot be negative");
        } else if (internalArray.length == 0) {
            add(n);
            return;
        } 
        else if (index > internalArray.length) {
            index = internalArray.length; //If index is bigger than list size, index = end of list 
        }

        feedArray(); //Adds one more index to the internal array

        for (int i = index; i < internalArray.length - 1; i++) {
            internalArray[i + 1] = internalArray[i]; //Shifts all elments after index to the right
        }

        internalArray[index] = n; 

    }

    /**
     * Gets the first instance in the list where element n shows up
     * @param n Element being checked 
     * @return Index at which the element shows up
     */
    public int indexof(int n) {
        for (int i = 0; i < internalArray.length; i++) {
            if (internalArray[i] == n) {
                return i;
            }
        }

        return -1;  
    }

    /**
     * Removes the element at specified index. 
     * @param index Specified index
     * @return The element being removed 
     */
    public int remove(int index) {

        if (index < 0 || internalArray.length == 0) { //Checks if index is negative or list is empty
            throw new IllegalArgumentException("Index out of bounds");
        } else if (index >= internalArray.length) {
            index = internalArray.length - 1;
        } 

        int removed = internalArray[index]; //New array to replace current array 
        int[] newArray = new int[internalArray.length - 1]; //Creates a new array with one less index

        for (int i = 0; i < index; i++) {
            newArray[i] = internalArray[i]; //Copies elemnets before the specified index over to new array
        }

        for (int i = index; i < internalArray.length - 1; i++) {
            newArray[i] = internalArray[i + 1]; //Copies elements after the specified index over to new array
        }

        internalArray = newArray; //Replaces the current array with the new array

        return removed; 
    }

    /**
     * Removes first instance of the element at the specified index
     * @param n Element being removed
     */
    public void removeValue(int n) {
        for (int i = 0; i < internalArray.length; i++) {
            if (internalArray[i] == n) {
                remove(i);
                return; 
            }
        }
    }

    /**
     * Removes all instances of int n
     * @param n Integer being removed 
     */
    public void removeall(int n) {
        for (int value : internalArray) {
            if (value == n) {
                removeValue(n);
            }
        }
    }

    /**
     * Calculates the mean of the integers in the list 
     * @return Mean value 
     */
    public double mean() {
        int sum = 0;

        if (internalArray.length == 0) {
            return 0; 
        }

        for (int num : internalArray) {
            sum += num; 
        }

        return sum/internalArray.length; 
    }

    /**
     * Calculates the variance of all the elements of the list 
     * @return Variance value 
     */
    public double variance() {  
        double sum = 0;
        double mean = mean();

        for (int i = 0; i < internalArray.length; i++) {
            sum += Math.pow((internalArray[i] - mean), 2); 
        }

        return sum/(internalArray.length - 1);
    }

    /**
     * Returns a new ArrayList with all elements of the original list that are within the lower and upper bounds 
     * @param lower Lower bound
     * @param upper Upper bound
     * @return Arraylist with all elements in between the bounds 
     */
    public ArrayList sublist(int lower, int upper) {
        ArrayList sublist = new ArrayList(); 

        for (int i = 0; i < internalArray.length; i++) {
            if (internalArray[i] >= lower && internalArray[i] <= upper) {
                sublist.add(internalArray[i]); 
            }
        }

        return sublist; 
    } 

    /**
     * Returns a new ArrayList with all elements of the original list that are within 3 standard deviations of mean
     * @return ArrayList with elements under 3 standard deviations of mean 
     */
    public ArrayList removeNoise() {
        double limit = Math.sqrt(variance()) * 3;
        double mean = mean(); 

        return sublist((int) Math.floor(mean - limit), (int) Math.ceil(mean + limit));
    }

    /**
     * Gets the length of the inernal array. 
     * @return Lenght of the internal array 
     */
    public int getSize() {
        return internalArray.length; 
    }

    /**
     * Adds one more index to the internal array 
     */
    private void feedArray() {
        int[] newArray = new int[internalArray.length + 1]; //Creates a new array with one more index 
        
        for (int i = 0; i < internalArray.length; i++) {
            newArray[i] = internalArray[i]; 
        }

        internalArray = newArray;
    }

    /**
     * Gets the element at the specified index
     * @param index Specified index
     * @return Element at the specified index
     */
    public int get(int index) {
        return internalArray[index]; 
    }
}