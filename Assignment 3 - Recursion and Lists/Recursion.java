/**
 * Recursion class that contains methods that use recursion to solve problems
 * @author Benjamin Zheng bxz346
 */

public class Recursion {

    /**
     * Calculates the sum of the digits of a number
     * @param n Number to calculate the sum of the digits
     * @return Sum of the digits of n
     */
    public int sumDigits(int n) {
        if (n == 0) {
            return 0;
        } else if (n < 0) {
            throw new IllegalArgumentException("Number must be positive");
        }   

        int remainder = n % 10;
        return remainder + sumDigits((n - remainder) / 10);
    }

    /**
     * Using Eclid's algorithm to find the greatest common divisor of two numbers
     * @param a Number 1 
     * @param b Number 2 
     * @return Greatest common divisor of a and b
     */
    public int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else if (a < 0 || b < 0) {
            throw new IllegalArgumentException("Numbers must be positive");
        }
        
        return gcd(b, a % b);
    }

    /**
     * Checks if inputed string is a palidrone 
     * @param str String to check if it is a palidrone
     * @return True if string is a palidrone, false otherwise
     */
    public boolean isPalindrome(String str) {
         if (str.length() <= 1) {
            return true; 
         }

         if (str.charAt(0) == str.charAt(str.length() - 1)) {
            return isPalindrome(str.substring(1, str.length() - 1));
         } 

         return false; 
    }

    public Node swapNodesInPairs(Node head) {
        if (head == null || !head.hasNext()) {
            return head;
        }

        Node nextHead = head.next().next(); //Specifies head for next recursive call 
        Node newHead = head.next(); //New head of the list
        Node next = head; //Next node in the list

        head = newHead; 
        head.setNext(next);
        next.setNext(swapNodesInPairs(nextHead));

        return head; 
    }

    /**
     * Calculates the binomial of n and k
     * @param n First number
     * @param k Second number
     * @return Binomial of n and k
     */
    public int binomial(int n, int k) {
        if (n < 0 || k < 0 || n < k) { //Checks if n and k are negative or n is less than k
            throw new IllegalArgumentException("Invalid Inputs for binomial");
        } 

        if (k == 0 || k == n) {
            return 1;
        } else {
            return (n * binomial(n - 1, k - 1)) / k;
        }
    }

    public void printNodes(Node node) {
        if (node == null) {
            return;
        }

        System.out.println(node.getElement());
        printNodes(node.next());
    }
    
}