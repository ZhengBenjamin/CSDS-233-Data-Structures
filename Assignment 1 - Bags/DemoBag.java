import java.util.*;

public class DemoBag {
    
    /**
     * Removes all occurrences of an element from bag
     * @param bag The bag being refered to 
     * @param element The element being removed 
     */
    public <T> void removeAll(DualImplementationBag<T> bag, T element) {
        while (bag.contains(element)) {
            bag.remove(element);
        }
    }

    /**
     * Retains only specified element 
     * @param bag The bag being refrenced 
     * @param element The element retained 
     */
    public <T> void retainAll(DualImplementationBag<T> bag, T element) {
        int i = 0; 
        while (i < bag.size()) {
            T currentElement = bag.get(i);
            if (!currentElement.equals(element)) {
                bag.remove(currentElement);
            } else {
                i++;
            }
        }
    }

    /**
     * Combines contents of two bags 
     * @param otherBag1 First bag to be combined 
     * @param otherBag2 Second bag to be combined 
     * @return Combined bag 
     */
    public <T> DualImplementationBag<T> union(DualImplementationBag<T> otherBag1, DualImplementationBag<T> otherBag2) {
        DualImplementationBag<T> newBag = new DualImplementationBag<T>(true);
        
        // Iterates through first bag copying to new bag 
        for (int i = 0; i < otherBag1.size(); i++) {
            newBag.add(otherBag1.get(i));
        }

        // Iterates through second bag copying ot new bag 
        for (int i = 0; i < otherBag2.size(); i++) {
            newBag.add(otherBag2.get(i));
        }

        return newBag;
    }

    /**
     * Creates new bag with elements common to both bags 
     * @param otherBag1 First bag to be combined
     * @param otherBag2 Second bag to be combined 
     * @return Bag with common elements 
     */
    public <T> DualImplementationBag<T> intersection(DualImplementationBag<T> otherBag1, DualImplementationBag<T> otherBag2) {
        DualImplementationBag<T> newBag = new DualImplementationBag<>(true);
        for (int i = 0; i < otherBag1.size(); i++) {
            if (otherBag2.contains(otherBag1.get(i))) {
                newBag.add(otherBag1.get(i));
            }
        }
        return newBag;
    }

    /**
     * Creates new bag with elements in one bag but not the other. 
     * @param otherBag1 First bag 
     * @param otherBag2 Second bag 
     * @return Bag with elements present in bag one but not bag two 
     */
    public <T> DualImplementationBag<T> difference(DualImplementationBag<T> otherBag1, DualImplementationBag<T> otherBag2) {
        DualImplementationBag<T> newBag = new DualImplementationBag<>(true);
        for (int i = 0; i < otherBag1.size(); i++) {
            if (!otherBag2.contains(otherBag1.get(i))) {
                newBag.add(otherBag1.get(i));
            }
        }
        return newBag;
    }


}
