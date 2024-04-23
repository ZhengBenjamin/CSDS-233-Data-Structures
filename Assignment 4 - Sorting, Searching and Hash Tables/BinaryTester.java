import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.*; 
import java.util.*;

public class BinaryTester extends Binary {

    private int[] largeArray;
    private int[] emtpy;
    
    @Before
    public void setup() {
        Random random = new Random();

        largeArray = new int[100000000];

        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = i;
        }

        emtpy = new int[0]; 

        shift(largeArray, random.nextInt(100000000));
    }

    @Test
    public void testBinarySearch() {
        assertEquals(0, findMin(largeArray)); //Minimum element is 0
        assertThrows(IllegalArgumentException.class, () -> findMin(emtpy)); //Empty array
    }

    /**
     * Shifts the elements of an array to the right by n
     * @param arr array to be shifted
     * @param n number of shifts
     */
    public void shift(int[] arr, int n) {
        int length = arr.length;
        n %= length;
    
        reverse(arr, 0, length - 1);
        reverse(arr, 0, n - 1);
        reverse(arr, n, length - 1);
    }
    
    private void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
    


}
