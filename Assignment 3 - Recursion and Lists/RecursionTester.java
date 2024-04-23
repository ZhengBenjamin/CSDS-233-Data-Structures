import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.Before;

public class RecursionTester extends Recursion {

    Node topNode;
    Node oddElements;  
    Node singleNode; 

    @Before
    public void setup() {
        topNode = new Node(0);
        singleNode = new Node(0);
        oddElements = new Node(0);

        Node nodePointer = topNode;

        for (int i = 1; i < 30000; i++) {
            nodePointer.setNext(new Node(i));
            nodePointer = nodePointer.next();
        }

        Node nodePointer2 = oddElements;

        for (int i = 1; i < 29999; i++) {
            nodePointer2.setNext(new Node(i));
            nodePointer2 = nodePointer2.next();
        }

    }

    @Test
    public void testSumDigits() {
        assertEquals(6, sumDigits(123));
        assertEquals(52, sumDigits(429496729));
        assertThrows(IllegalArgumentException.class, () -> sumDigits(-1));
    }

    @Test
    public void testGCD() {
        assertEquals(3, gcd(9, 6)); //Postive 
        assertEquals(1, gcd(17, 23));
        assertEquals(5, gcd(25, 15));

        assertThrows(IllegalArgumentException.class, () -> gcd(-1, 6)); //Negative

        assertEquals(6, gcd(0, 6)); //Zeros
        assertEquals(17, gcd(17, 0));
        assertEquals(0, gcd(0, 0));
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(isPalindrome(""));
        assertTrue(isPalindrome("a"));
        assertTrue(isPalindrome("racecar"));
        assertTrue(isPalindrome("madam"));
        assertTrue(isPalindrome("level"));
        assertTrue(isPalindrome("deed"));
        
        assertFalse(isPalindrome("hello"));
        assertFalse(isPalindrome("world"));
        assertFalse(isPalindrome("goodbye"));
        assertFalse(isPalindrome("apple"));
        assertFalse(isPalindrome("aabcaa"));

        assertTrue(isPalindrome("aabbccdd ada ddccbbaa"));
    }

    @Test
    public void testNodesInPairs() {
        Node result1 = swapNodesInPairs(topNode); //Stress test with large even number of elements
        for (int i = 0; i < 30000; i += 2) {
            assertEquals(i + 1, result1.getElement());
            result1 = result1.next();
            assertEquals(i, result1.getElement());
            result1 = result1.next();
        }

        Node result2 = swapNodesInPairs(oddElements); //Stress test with large odd number of elements
        for (int i = 0; i < 29998; i += 2) {
            assertEquals(i + 1, result2.getElement());
            result2 = result2.next();
            assertEquals(i, result2.getElement());
            result2 = result2.next();
        }

        Node result3 = swapNodesInPairs(singleNode);
        assertEquals(0, result3.getElement()); //Single node test
        assertFalse(result3.hasNext());
    }

    @Test
    public void testBinomial() {
        assertEquals(1, binomial(0, 0));
        assertEquals(293930, binomial(21, 12));
        assertEquals(216071394, binomial(123, 5));
        assertThrows(IllegalArgumentException.class, () -> binomial(-1, 6)); //Negative Values
        assertThrows(IllegalArgumentException.class, () -> binomial(6, 7)); //n is less than k
    }

    /* Helper method to print out nodes for testing 
    public void printNodes(Node node) {
        while(node.hasNext()) {
            System.out.println(node.getElement());
            node = node.next();
        }

        System.out.println(node.getElement());
    }
    */

}
