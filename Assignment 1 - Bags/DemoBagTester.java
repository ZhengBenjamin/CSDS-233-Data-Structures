import java.util.*;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DemoBagTester<T> extends DemoBag {
    
    public DualImplementationBag emptyLinkedList; //Predefined empty linked list 
    public DualImplementationBag emptyArrayBag; //Predefined empty array bag 
    public DualImplementationBag smallLinkedList; //Predefined bag linked list with small number of elements 
    public DualImplementationBag smallArrayList; //Predefeind bag array list with small number of elements 
    public DualImplementationBag smallLinkedList2; //Predefeind bag linked list with small number of elements 
    public DualImplementationBag bigLinkedList; //Predefeind bag linked list with large number of elements
    public DualImplementationBag bigArrayList; //Predefeind bag array list with large number of elements 
    public DualImplementationBag random; //Predefeind bag with random elements 

    public Object[] randomArray;

    @Before 
    public void setup() {

        emptyLinkedList = new DualImplementationBag<>(false);
        emptyArrayBag = new DualImplementationBag<>(true);

        smallLinkedList = new DualImplementationBag<>(false);
        smallLinkedList.add(1);
        smallLinkedList.add(2);
        smallLinkedList.add(3.0);

        smallArrayList = new DualImplementationBag<>(true);
        smallArrayList.add(1);
        smallArrayList.add(2);
        smallArrayList.add(3.0);

        smallLinkedList2 = new DualImplementationBag<>(false);
        smallLinkedList2.add(1);
        smallLinkedList2.add(3.0);
        smallLinkedList2.add(7);
        smallLinkedList2.add(7);
        smallLinkedList2.add(7);

        bigLinkedList = new DualImplementationBag<>(false);
        for (int i = 0; i < 10000; i++) {
            bigLinkedList.add(i);
        }

        bigArrayList = new DualImplementationBag<>(true);
        for (int i = 0; i < 10000; i++) {
            bigArrayList.add(i);
        }

        random = new DualImplementationBag<>(false);
        
        randomArray = new Object[]{};
        random.add("Uzair");
        random.add(1);
        random.add(randomArray);
    }

    @Test 
    public void testRemoveAll() {
        
        // Test with empty bag
        removeAll(emptyLinkedList, 1);
        assertTrue(emptyLinkedList.isEmpty());

        removeAll(emptyArrayBag, 1);
        assertTrue(emptyArrayBag.isEmpty());
        
        // Test small Linked List 
        removeAll(smallLinkedList, "Uzair's mom");
        removeAll(smallArrayList, "Uzair's mom");

        Object[] testList = new Object[]{1, 2, 3.0};
        
        for (int i = 0; i < smallLinkedList.size(); i++) {
            assertEquals(smallLinkedList.get(i), testList[i]);
            assertEquals(smallArrayList.get(i), testList[i]);
        }
        
        // Test bag with random types
        removeAll(random, "Uzair");
        assertFalse(random.contains("Uzair"));
    }

    @Test 
    public void testRetainAll() {

        // Test with empty bag; Produces empty bag 
        retainAll(emptyLinkedList, 1);
        assertTrue(emptyLinkedList.isEmpty());

        retainAll(emptyArrayBag, 1);
        assertTrue(emptyArrayBag.isEmpty());

        // Test with small bag 
        retainAll(smallLinkedList, 1);
        
        for (int i = 0; i < smallLinkedList.size(); i++) {
            assertEquals(smallLinkedList.get(i), 1);
        }

        retainAll(smallArrayList, 1);
        
        for (int i = 0; i < smallArrayList.size(); i++) {
            assertEquals(smallArrayList.get(i), 1);
        }

        // Test with repeating elements 
        retainAll(smallLinkedList, 7);

        for (int i = 0; i < smallLinkedList.size(); i++) {
            assertEquals(smallLinkedList.get(i), 7);
        }

        // Test with big bag 
        retainAll(bigLinkedList, 40);
        
        for (int i = 0; i < bigLinkedList.size(); i++) {
            assertEquals(bigLinkedList.get(i), 40);
        }

        retainAll(bigArrayList, 40);
        
        for (int i = 0; i < bigArrayList.size(); i++) {
            assertEquals(bigArrayList.get(i), 40);
        }

        // Test with random type bag 

        retainAll(random, "Uzair");
        
        for (int i = 0; i < random.size(); i++) {
            assertEquals(random.get(i), "Uzair");
        }
        
    }

    @Test
    public void testUnion() {

        // Test with empty bag
        assertTrue(union(emptyLinkedList, emptyArrayBag).isEmpty());

        // Test with small bag - Different implementations
        DualImplementationBag testBag1 = union(smallArrayList, smallLinkedList);
        
        for (int i = 0; i < smallArrayList.size(); i++) {
            assertTrue(testBag1.contains(smallArrayList.get(i)));
        }
        for (int i = 0; i < smallLinkedList.size(); i++) {
            assertTrue(testBag1.contains(smallArrayList.get(i)));
        }

        // Test with small bag - Same implementation
        DualImplementationBag testBag2 = union(smallLinkedList, smallLinkedList2);
        
        for (int i = 0; i < smallLinkedList.size(); i++) {
            assertTrue(testBag2.contains(smallArrayList.get(i)));
        }
        for (int i = 0; i < smallLinkedList2.size(); i++) {
            assertTrue(testBag2.contains(smallLinkedList2.get(i)));
        }

        // Test with big bag - Same implementation
        DualImplementationBag testBag3 = union(bigLinkedList, bigLinkedList);
        
        for (int i = 0; i < bigLinkedList.size(); i++) {
            assertTrue(testBag3.contains(bigLinkedList.get(i)));
        }

        // Test with big bag - Different implementations
        DualImplementationBag testBag4 = union(bigLinkedList, bigArrayList);
        
        for (int i = 0; i < bigLinkedList.size(); i++) {
            assertTrue(testBag4.contains(bigLinkedList.get(i)));
        }
        for (int i = 0; i < bigArrayList.size(); i++) {
            assertTrue(testBag4.contains(bigArrayList.get(i)));
        }

        // Test with random type 
        DualImplementationBag testBag5 = union(smallLinkedList, random);

        for (int i = 0; i < smallLinkedList.size(); i++) {
            assertTrue(testBag5.contains(smallLinkedList.get(i)));
        }
        for (int i = 0; i < random.size(); i++) {
            assertTrue(testBag5.contains(random.get(i)));
        }
    }

    @Test
    public void testIntersection() {

        // Test with empty bag 
        assertTrue(intersection(emptyLinkedList, emptyArrayBag).isEmpty());

        // Test with small bag - Different implementations
        DualImplementationBag testBag1 = intersection(smallArrayList, smallLinkedList);
        Object[] testList1 = new Object[]{1, 2, 3.0};
        for (int i = 0; i < testBag1.size(); i++) {
            assertEquals(testBag1.get(i), testList1[i]);
        }

        // Test with small bag - Same implementation
        DualImplementationBag testBag2 = intersection(smallLinkedList, smallLinkedList2);
        Object[] testList2 = new Object[]{1, 3.0};
        for (int i = 0; i < testBag2.size(); i++) {
            assertEquals(testBag2.get(i), testList2[i]);
        }

        // Test with big bag
        DualImplementationBag testBag3 = intersection(bigLinkedList, bigArrayList);
        Object[] testList3 = new Object[500];
        for (int i = 0; i < 500; i++) {
            testList3[i] = i;
        }
        for (int i = 0; i < testBag3.size(); i++) {
            assertEquals(testBag3.get(0), testList3[0]);
        }

        // Test with random types 
        DualImplementationBag testBag4 = intersection(smallLinkedList, random);
        Object[] testList4 = new Object[]{1};
        for (int i = 0; i < testBag4.size(); i++) {
            assertEquals(testBag4.get(i), testList4[i]);
        }

    }

    @Test
    public void difference() {

        // Test with empty bag 
        assertTrue(difference(emptyLinkedList, emptyArrayBag).isEmpty());

        // Test with small bag; Different implimentaion, same elements 
        DualImplementationBag testBag1 = difference(smallLinkedList, smallArrayList);
        assertTrue(testBag1.isEmpty());

        // Test with small bag; Same implementation, different elements
        DualImplementationBag testBag2 = difference(smallLinkedList, smallLinkedList2);
        Object[] testList1 = new Object[]{2};
        for (int i = 0; i < testBag2.size(); i++) {
            assertEquals(testBag2.get(i), testList1[i]);
        }

        // Test with repeating elements 
        DualImplementationBag testBag3 = difference(smallLinkedList2, smallLinkedList);
        Object[] testList2 = new Object[]{7,7,7};
        for (int i = 0; i < testBag3.size(); i++) {
            assertEquals(testBag3.get(i), testList2[i]);
        }

        // Test with big bags
        DualImplementationBag testBag4 = difference(bigArrayList, bigLinkedList);
        Object[] testList3 = new Object[10000];
        for (int i = 0; i < 10000; i++) {
            testList3[i] = i;
        }
        for (int i = 0; i < testBag4.size(); i++) {
            assertEquals(testBag4.get(i), testList3[i]);
        }

        // Test with random types 
        DualImplementationBag testBag5 = difference(random, smallArrayList);
        Object[] testList4 = new Object[]{"Uzair", randomArray};
        for (int i = 0; i < testBag5.size(); i++) {
            assertEquals(testBag5.get(i), testList4[i]);
        }
    }

}
