import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import static org.junit.Assert.assertTrue;

public class RandomQuickSortTester extends RandomQuickSort {

    Integer[] largeRandom; //Array of random integers
    Integer[] largeRandom2; //Array of random integers
    Integer[] empty; //Empty array

    @Before
    public void setUp() {
        Random random = new Random(); 
        
        largeRandom = new Integer[90000000]; //Is this enough elements for stress tests Eduardo Bautista Uribe?
        largeRandom2 = new Integer[50000000]; //I hope you have enough memory.
        empty = new Integer[0]; //Empty array 

        for (int i = 0; i < largeRandom.length; i++) {
            largeRandom[i] = random.nextInt(90000000); 
        }

        for (int i = 0; i < largeRandom2.length; i++) {
            largeRandom2[i] = random.nextInt(50000000); 
        }

    }

    @Test
    public void testRandomQuickSort() {
        quickSort(largeRandom, 0, largeRandom.length - 1); //Sorts array
        assertTrue(check(largeRandom)); //Checks if array is sorted

        quickSort(largeRandom2, 100, 200); //Sorts Inteval
        Integer[] checkInt = new Integer[100];
        for (int i = 0; i < 100; i++) {
            checkInt[i] = largeRandom2[i + 100];
        }
        assertTrue(check(checkInt)); //Checks if array is sorted

        quickSort(empty, 0, 1); //Sorts empty array & invalid index
        assertTrue(check(empty)); //Checks if array is sorted
    }

    /**
     * Helper method to check if array is sorted
     * @param nums array to be checked
     * @return true if array is sorted, false otherwise
     */
    public boolean check(Integer[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
