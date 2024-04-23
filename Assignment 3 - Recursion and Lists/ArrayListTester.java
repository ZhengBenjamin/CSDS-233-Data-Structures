import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;
import org.junit.Before;

public class ArrayListTester {
    
    private ArrayList emptyList; //Empty list 
    private ArrayList largeList; //Predefined list with large number of elements
    private ArrayList repeatingList; //List with repeating elements
    private ArrayList repeatingList2; //List with different repeating elements 

    @Before 
    public void setup() {
        emptyList = new ArrayList();
        largeList = new ArrayList(); 
        repeatingList = new ArrayList();
        repeatingList2 = new ArrayList();

        for (int i = 0; i < 10000; i++) {
            largeList.add(i);
            repeatingList.add(12); 
        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 10; j++) {
                repeatingList2.add(i);
            }
        }

    }

    @Test 
    public void testAdd() {
        for (int i = 0; i < 100000; i++) { //Stress test with 100000 elements
            emptyList.add(i);
            assertEquals(i, emptyList.get(i));
        }

        for (int i = 10000; i < 20000; i++) { //Stress test with list containing predefeind elements 
            largeList.add(i);
            assertEquals(i, largeList.get(i));
        }
    }

    @Test
    public void testAddIndex() {
        emptyList.add(1, 0); //Adds element at index 1 
        assertEquals(emptyList.get(0), 1);

        emptyList.add(2, 100); //Adds element at index bigger than Array size 
        assertEquals(emptyList.get(1), 2);

        largeList.add(15, 0); //Adds element to front of large list 
        assertEquals(largeList.get(0), 15);

        largeList.add(15, 10000); //Adds element to end of large list
        assertEquals(largeList.get(10000), 15);

        assertThrows(IllegalArgumentException.class, () -> {
            emptyList.add(1, -1); //Negative index 
        });
    }

    @Test 
    public void testIndexOf() {
        assertEquals(-1 , emptyList.indexof(1)); //Empty list
        
        for (int i = 0; i < 10000; i++) { //Limit test with large list
            assertEquals(i, largeList.indexof(i));
        }
    }

    @Test 
    public void testRemove() {
        assertThrows(IllegalArgumentException.class, () -> {
            emptyList.remove(-1); //Negative index 
        });

        assertThrows(IllegalArgumentException.class, () -> {
            emptyList.remove(0); //Empty list; Out of bounds 
        });

        emptyList.add(1);
        assertEquals(1, emptyList.remove(0)); //Remove element from list with one element
        assertEquals(-1, emptyList.indexof(1)); //Check if element is removed

        int i = 0;
        while (i < 5000) { //Stress test by removing 5000 elements from the middle of large list
            int number = largeList.get(i);
            largeList.remove(i);
            assertEquals(largeList.get(i), number + 1);
            i++; 
        }
    }

    @Test
    public void removeValue() {

        for (int i = 0; i < 9000; i++) { //Stress test, removes 9000 elements from large list
            largeList.removeValue(i);
        }
        
        int num = 9000;
        for (int i = 0; i < 1000; i++) { //Checks if remaining elements are correct
            assertEquals(num, largeList.get(i));
            num++; 
        }

        emptyList.removeValue(1); //Empty list
        assertEquals(emptyList.getSize(), 0); //Check if list is empty

        emptyList.add(12);
        emptyList.removeValue(13); //Element not in list
        assertEquals(1, emptyList.getSize()); //Check if list is still the same size
    }

    @Test
    public void testRemoveAll() {
        emptyList.removeall(1); //Empty list
        assertEquals(emptyList.getSize(), 0); //Check if list is empty

        repeatingList.removeall(12); //Stress test, removes all instances of 12 in repeating list 
        assertEquals(0, repeatingList.getSize()); //Check if list is empty
 
        repeatingList2.removeall(0);
        assertEquals(990, repeatingList2.getSize()); //Check if list is the correct size after removing all instances of 0
        assertEquals(1, repeatingList2.get(0)); //Check if first element is correct
    }

    @Test
    public void testMean() {
        assertEquals(0, emptyList.mean(), 1); //Empty list
        assertEquals(4999.5, largeList.mean(), 1); //Mean of large list
        assertEquals(12, repeatingList.mean(), 1); //Mean of repeating list
        assertEquals(49, repeatingList2.mean(), 1); //Mean of repeating list 2
    }

    @Test
    public void testVariance() {
        assertEquals(0, emptyList.variance(), 1); //Empty list
        assertEquals(8334166, largeList.variance(), 1); //Variance of large list
        assertEquals(0, repeatingList.variance(), 1); //Variance of repeating list
        assertEquals(834, repeatingList2.variance(), 1); //Variance of repeating list 2
    }

    @Test 
    public void testSublist() {
        ArrayList list = largeList.sublist(0, 1000); //Sublist of large list
        assertEquals(1001, list.getSize()); //Check if sublist is the correct size

        for (int i = 0; i < 1000; i++) { //Check if sublist contains the correct elements
            assertEquals(i, list.get(i));
        }

        list = largeList.sublist(0, 5000); //Sublist of large list
        assertEquals(5001, list.getSize()); //Check if sublist is the correct size

        for (int i = 0; i < 5000; i++) { //Check if sublist contains the correct elements
            assertEquals(i, list.get(i));
        }

        list = largeList.sublist(5000, 10000); //Sublist of large list
        assertEquals(5000, list.getSize()); //Check if sublist is the correct size

        for (int i = 0; i < 5000; i++) { //Check if sublist contains the correct elements
            assertEquals(i + 5000, list.get(i));
        } 
    }

    @Test
    public void testRemoveNoise() {
        ArrayList list = largeList.removeNoise(); //Removes noise from large list
        assertEquals(list.get(0), 0); //Check if first element is correct
        assertEquals(list.get(list.getSize() - 1), 9999); //Check if last element is correct

        ArrayList list2 = repeatingList.removeNoise(); //Removes noise from repeating list
        assertEquals(list2.get(0), 12);
        assertEquals(list2.get(list.getSize() - 1), 12);

        ArrayList list3 = emptyList.removeNoise(); //Empty list
        assertEquals(list3.getSize(), 0); //Check if list is empty

        ArrayList list4 = repeatingList2.removeNoise(); //Removes noise from repeating list 2
        assertEquals(list4.get(0), 0);
        assertEquals(list4.get(list4.getSize() - 1), 99);
    }

}