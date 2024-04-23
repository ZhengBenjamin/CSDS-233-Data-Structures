import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DualImplementationBagTester<T> {

    public DualImplementationBag emptyLinkedList; // Empty Linked List bag 
    public DualImplementationBag emptyArrayList; // Empty Array List bag 
    public DualImplementationBag smallLinkedList; // Predefeind linked list bag 
    public DualImplementationBag smallArrayList; // Predefined array list with integers 
    public DualImplementationBag stringLinkedList; // Predefined linked list with strings
    public DualImplementationBag stringArrayList; // Predefined array list with strings 
    public DualImplementationBag bigLinkedList; // Predefined linked list with large amounts of items 
    public DualImplementationBag bigArrayList; // Predefeind array list with large amounts of items 
    public DualImplementationBag random; //Predefeind array with random objects 

    @Before
    public void setup() {
        emptyLinkedList = new DualImplementationBag<>(false);
        emptyArrayList = new DualImplementationBag<>(true);

        smallLinkedList = new DualImplementationBag<>(false);
        smallArrayList = new DualImplementationBag<>(true);

        stringLinkedList = new DualImplementationBag<String>(false);
        stringLinkedList.add("One");
        stringLinkedList.add("Two");
        stringLinkedList.add("Uzair");

        stringArrayList = new DualImplementationBag<String>(true);
        stringArrayList.add("Uno");
        stringArrayList.add("Dos");
        stringArrayList.add("Benjamin");

        bigLinkedList = new DualImplementationBag<>(false);
        bigArrayList = new DualImplementationBag<>(true);

        for (int i = 0; i < 10; i++) {
            smallLinkedList.add(i);
            smallArrayList.add(i);
        }

        for (int i = 0; i < 9999; i++) {
            bigLinkedList.add(i);
            bigArrayList.add(i);
        }

        random = new DualImplementationBag<>(false);

        Object[] randomArray = new Object[]{};

        random.add("Uzair");
        random.add(42);
        random.add(randomArray);
    }

    @Test
    public void testDualImplementationBag() {
        assertTrue(emptyArrayList.isArrayList());
        assertFalse(emptyLinkedList.isArrayList());
    }

    @Test 
    public void testSetUseArrayList() {

        // Convert empty array list to linked list 
        emptyArrayList.setUseArrayList(false);
        assertFalse(emptyArrayList.isArrayList());

        // Convert empty linked lsit to array list 
        emptyLinkedList.setUseArrayList(true);
        assertTrue(emptyLinkedList.isArrayList());

        // Convert array list to linked list 
        smallLinkedList.setUseArrayList(true);
        int[] testList1 = new int[]{0,1,2,3,4,5,6,7,8,9};
        assertTrue(smallLinkedList.isArrayList());
        for (int i = 0; i < smallLinkedList.size(); i++) {
            assertEquals(smallLinkedList.get(i), testList1[i]);
        }

        // Convert linked list to array list 
        smallArrayList.setUseArrayList(false);
        int[] testList2 = new int[]{0,1,2,3,4,5,6,7,8,9};
        assertFalse(smallArrayList.isArrayList());
        for (int i = 0; i < smallArrayList.size(); i++) {
            assertEquals(smallArrayList.get(i), testList2[i]);
        }

        // Test String 
        stringLinkedList.setUseArrayList(true);
        String[] testList3 = new String[]{"One", "Two", "Uzair"};
        assertTrue(stringLinkedList.isArrayList());
        for (int i = 0; i < stringLinkedList.size(); i++) {
            assertEquals(stringLinkedList.get(i), testList3[i]);
        }
    }

    @Test
    public void testAdd() {

        // Tests adding to empty linked list 
        emptyLinkedList.add(1);
        emptyLinkedList.add(2);
        emptyLinkedList.add(3);

        int[] testAdd1 = new int[]{1,2,3};

        for (int i = 0; i < emptyLinkedList.size(); i++) {
            assertEquals(emptyLinkedList.get(i), testAdd1[i]);
        }

        // Tets adding to empty array list 
        emptyArrayList.add(1);
        emptyArrayList.add(2);
        emptyArrayList.add(3);

        for (int i = 0; i < emptyArrayList.size(); i++) {
            assertEquals(emptyArrayList.get(i), testAdd1[i]);
        }

        // Tests String 
        stringArrayList.add("Apple");
        assertTrue(stringArrayList.contains("Apple"));

        stringLinkedList.add("Pear");
        assertTrue(stringLinkedList.contains("Pear"));

        // Adding different types 
        Object[] randomObject = new Object[]{};

        smallArrayList.add("Dog");
        smallArrayList.add(4.5);
        smallArrayList.add(randomObject);

        assertTrue(smallArrayList.contains("Dog"));
        assertTrue(smallArrayList.contains(4.5));
        assertTrue(smallArrayList.contains(randomObject));

        // Very large bag 
        int[] testAdd2 = new int[10000];
        for (int i = 0; i < 9999; i++) {
            testAdd2[i] = i;
        }
        testAdd2[9999] = 69;
        bigLinkedList.add(69);

        for (int i = 0; i < bigLinkedList.size(); i++) {
            assertEquals(bigLinkedList.get(i), testAdd2[i]);
        }
    }

    @Test
    public void testRemove() {
        
        // Tests remove on linked list 
        smallLinkedList.remove(9);
        smallLinkedList.remove(5);
        smallLinkedList.remove(0);

        // Tests remove on array list 
        smallArrayList.remove(9);
        smallArrayList.remove(5);
        smallArrayList.remove(0);

        // Tests remove on small bags 
        int[] testRemove1 = new int[]{1,2,3,4,6,7,8,9};

        for (int i = 0; i < smallArrayList.size(); i++) {
            assertEquals(smallArrayList.get(i), testRemove1[i]);
        }

        for (int i = 0; i < smallLinkedList.size(); i++) {
            assertEquals(smallLinkedList.get(i), testRemove1[i]);
        }

        // Testing removing elements not in the bag 
        assertFalse(smallLinkedList.remove(21));
        assertFalse(smallArrayList.remove(21));

        // Testing removing from empty bag 
        assertFalse(emptyArrayList.remove(1));
        assertFalse(emptyLinkedList.remove(1));

        // Remove different objects
        random.remove("Uzair");
        random.remove(42);

        assertFalse(random.contains("Uzair"));
        assertFalse(random.remove(42));

    }

    @Test
    public void testContains() {

        //Testing elements in the bag 
        assertTrue(smallLinkedList.contains(0));
        assertTrue(smallLinkedList.contains(5));
        assertTrue(smallLinkedList.contains(9));

        assertTrue(smallArrayList.contains(0));
        assertTrue(smallArrayList.contains(5));
        assertTrue(smallArrayList.contains(9));

        // Testing random objects 
        assertTrue(random.contains("Uzair"));
        assertTrue(random.contains(42));

        // Testing elements not in the bag 
        assertFalse(smallLinkedList.contains(21));
        assertFalse(smallLinkedList.contains("Dookie"));

        assertFalse(smallArrayList.contains(21));
        assertFalse(smallArrayList.contains("Dookie"));
    }

    @Test
    public void testIfEmpty() {

        // Tests on empty bags 
        assertTrue(emptyLinkedList.isEmpty());
        assertTrue(emptyArrayList.isEmpty());

        // Tests on small bags 
        assertFalse(smallLinkedList.isEmpty());
        assertFalse(smallArrayList.isEmpty());

        // Tests on large bags 
        assertFalse(bigArrayList.isEmpty());
        assertFalse(bigLinkedList.isEmpty());

        // Test on random objects 
        assertFalse(random.isEmpty());
        assertFalse(stringArrayList.isEmpty());

    }

    @Test 
    public void testSize() {

        // Tests empty bags 
        assertEquals(0, emptyArrayList.size());
        assertEquals(0, emptyLinkedList.size());

        // Tests small bags 
        assertEquals(10, smallLinkedList.size());
        assertEquals(10, smallArrayList.size());

        //Tests large bags 
        assertEquals(9999, bigArrayList.size());
        assertEquals(9999, bigLinkedList.size());

        //Test with random elements 
        assertEquals(3, random.size());

    }

}
