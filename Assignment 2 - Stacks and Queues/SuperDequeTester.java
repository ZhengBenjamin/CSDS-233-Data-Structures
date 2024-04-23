import static org.junit.Assert.*;

import org.junit.*; 

public class SuperDequeTester extends SuperDeque {
    
    public SuperDeque<Object> empty; //Empty data structure 
    public SuperDeque<Integer> intSuperDeque; //SuperDeque with predefined integers 
    public SuperDeque<String> stringSuperDeque; //SuperDeque with predefeind strings
    public SuperDeque<Object> randomSuperDeque; //SuperDeque with predefeind random objects 

    @Before
    public void setup() {
        empty = new SuperDeque<>(); 
        intSuperDeque = new SuperDeque<>(); 
        stringSuperDeque = new SuperDeque<>(); 
        randomSuperDeque = new SuperDeque<>(); 

        for (int i = 1; i <= 100; i++) { //Puts integers 1 - 100 into the data structure 
            intSuperDeque.push(i);
            stringSuperDeque.push(String.valueOf(i));
        }
        
        Object random = 1; 

        //Adds random data types to random 
        randomSuperDeque.push(1);
        randomSuperDeque.push("Tamanna");
        randomSuperDeque.push("Asil");
        randomSuperDeque.push(2.0);
        randomSuperDeque.push(random);

    }

    @Test
    public void pushTester() {
        empty.push(1);
        assertTrue(empty.peek().equals(1)); //Pushing to empty SuperDeque 

        empty.push(2);
        assertTrue(empty.peek().equals(2)); //Pushing to one element SuperDeque 

        intSuperDeque.push(1);
        assertTrue(intSuperDeque.peek().equals(1)); //Pushes to SupreDeque with large number of elements

        stringSuperDeque.push("Hello!");
        assertTrue(stringSuperDeque.peek().equals("Hello!")); //Pushes to string SuperDeque 
        
        randomSuperDeque.push(":D");
        assertTrue(randomSuperDeque.peek().equals(":D")); //Pushes to random object SuperDeque 

        int[] checkTest1 = new int[100]; //Check Test 

        for (int i = 1; i <= 100; i++) {
            empty.push(i);
        }
        for (int i = 100; i >= 1; i--) {
            checkTest1[100 - i] = i; 
        }

        for (int i = 0; i <- 100; i++) { //Limit test 100 elements 
            assertTrue(empty.pop().equals(checkTest1[i]));
        }

    }

    @Test
    public void popTester() {
        assertNull(empty.pop()); //Pops from empty SuperDeque 
        assertTrue(intSuperDeque.pop().equals(100)); //Pops from predefeind int SuperDeque
        assertTrue(stringSuperDeque.pop().equals("100")); //Pops from predefeind String SuperDeque 
        assertTrue(randomSuperDeque.pop().equals(1)); //Pops from predefined random Object SuperDeque 
        
        for (int i = 1; i < 100; i++) {
            assertEquals(100 - i, (int) intSuperDeque.pop()); //Limit test; Checks 99 elements 
        }
    }

    @Test
    public void peekTester() {
        assertNull(empty.peek()); //Peeks from empty SuperDeque 
        assertTrue(intSuperDeque.peek().equals(100)); //Peeks from predefeind int SuperDeque
        assertTrue(stringSuperDeque.peek().equals("100")); //Peeks from predefeind String SuperDeque 
        assertTrue(randomSuperDeque.peek().equals(1)); //Peeks from predefined random Object SuperDeque 
    }

    @Test
    public void enqueueTester() {
        empty.enqueue(1);
        assertEquals(1, checkBack(empty)); //Enques to an empty SuperDeque 

        intSuperDeque.enqueue(100);
        assertEquals(100, (int) checkBack(intSuperDeque)); //Enques to an int SuperDeque with predefined existing elements 
        
        stringSuperDeque.enqueue("Kanye");
        assertEquals("Kanye", checkBack(stringSuperDeque)); //Enqueues to a String SuperDeque with predefeind existing elements 

        Object random = 12; 
        randomSuperDeque.enqueue(random);
        assertEquals(12, checkBack(randomSuperDeque)); //Enqueues to a random object SuperDeque 
    }

    @Test
    public void dequeueTester() {
        assertNull(empty.dequeue()); //Dequeue from empty SuperDeque 
        assertTrue(intSuperDeque.dequeue().equals(100)); //Dequeue from predefeind int SuperDeque
        assertTrue(stringSuperDeque.dequeue().equals("100")); //Dequeue from predefeind String SuperDeque 
        assertTrue(randomSuperDeque.dequeue().equals(1)); //Dequeue from predefined random Object SuperDeque 
        
        for (int i = 1; i < 100; i++) {
            assertEquals(100 - i, (int) intSuperDeque.dequeue()); //Limit test; Checks 99 elements 
        }
    }

    @Test 
    public void isEmptyTester() {
        assertTrue(empty.isEmpty()); //Checks if correctly identifies empty SuperDeque 
        assertFalse(intSuperDeque.isEmpty()); //Checks if correctly identifies non empty SuperDeque 
    }

    @Test
    public void testToString() {
        empty.push(69);
        empty.push(68);
        empty.push(67);
        empty.push(66);
        empty.push(65);
        
        assertEquals("65, 66, 67, 68, 69", empty.toString());
    }
    
    /**
     * Checks if a SuperDeque contains an element 
     * @param dq SuperDeque being checked 
     * @param element Element checking for 
     * @return If SuperDeque contains the element 
     */
    private <E> boolean contains(SuperDeque<E> dq, E element) {
        while (!dq.isEmpty()) {
            if (dq.pop().equals(element)) {
                return true;
            }
        }
        return false; 
    }

    /**
     * Returns the very back of a SuperDeque 
     * @param dq Target SuperDeque 
     * @return The last element of SuperDeque 
     */
    private <E> E checkBack(SuperDeque<E> dq) {
        SuperDeque<E> tempStack = new SuperDeque<>(); 

        while (!dq.isEmpty()) {
            tempStack.push(dq.pop());
        }
        
        E element = tempStack.peek();

        while(!tempStack.isEmpty()) {
            dq.push(tempStack.pop()); 
        }
        
        return element; 
    }
}
