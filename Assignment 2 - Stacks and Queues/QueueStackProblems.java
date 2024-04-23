public class QueueStackProblems {

    /**
     * Evaulates a postfix expression
     * @param postfix Expression being evaulated 
     * @return Value of postfix expression 
     */
    public static int evaluatePostFix(String postfix) {
        SuperDeque<Character> operator = new SuperDeque<>(); //Stack for operators 
        SuperDeque<Integer> operand = new SuperDeque<>(); //Stack for operands 
        boolean lastDigit = false; //If has last digit 
        int currentNum = 0; //Variable for building numbers from string 
        
        for (int i = 0; i < postfix.length(); i++) { //Iterates through the postfix string and computes results 
            if (Character.isDigit(postfix.charAt(i)) && lastDigit == false){ //Converts string numbers into int 
                while ((i <= postfix.length() - 1) && Character.isDigit(postfix.charAt(i))) {
                    currentNum = currentNum * 10 + Character.getNumericValue(postfix.charAt(i));
                    i++;
                }
                operand.push(currentNum); //Pushes int into stack 
                currentNum = 0; 
                
                //If operator is found, compute the operator with operands 
            } else if (postfix.charAt(i) == '+' || postfix.charAt(i) == '-' || postfix.charAt(i) == '*' ||  postfix.charAt(i) == '/' ||  postfix.charAt(i) == '%') {
                
                operator.push(postfix.charAt(i));
                int num2 = operand.pop();
                int num1 = operand.pop();
                int result = 0;

                switch(operator.pop()) { //Computes operator with operands
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        result = num1 / num2;
                        break;
                    case '%':
                        result = num1 % num2;
                        break;
                }
                operand.push(result); //Puts result back into the operand stack 
            }
            lastDigit = Character.isDigit(postfix.charAt(i)); //Updates last digit 
        }
        return operand.pop(); //Last element in operand stack is the result 
    }

    /**
     * Reverses the inputed string 
     * @param s String being reversed 
     * @return Reversed string 
     */
    public static String reverseWords(String s) {
        SuperDeque<Character> stack = new SuperDeque<>(); 
        StringBuilder reverse = new StringBuilder(); 
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') { //If not space, then push element into stack 
                stack.push(s.charAt(i)); 
            }
            
            if (s.charAt(i) == ' ') { //If space, empty the stack and reverse chracters will be put in StringBuilder 
                while (!stack.isEmpty()) {
                    reverse.append(stack.pop()); 
                }
                reverse.append(' '); 
            }
        }

        while (!stack.isEmpty()) { //Empties the last word into the StringBuilder 
            reverse.append(stack.pop()); 
        }

        if (reverse.charAt(reverse.length() - 1) == ' ') { //Removes an extra space if there is any at the end of the string 
            reverse.deleteCharAt(reverse.length());
        } 

        return reverse.toString(); 
    }

    /**
     * Reverses the first K elements of the inputed super deque
     * Reverses all elements if k is larger than super deque size 
     * @param dq Object being reversed 
     * @param k First number of elements to be reversed 
     * @return Reversed super deque object
     */
    public static <E> SuperDeque<E> reverseK(SuperDeque<E> dq, int k) {
        SuperDeque<E> temp = new SuperDeque<>(); 
        SuperDeque<E> temp2 = new SuperDeque<>(); 
        
        if (k > getCurrentSize(dq)) { //Using k as an index 
            k = getCurrentSize(dq);
        }

        for (int i = 0; i < k; i++) { //Puts last k elements into a stack 
            temp.push(dq.pop()); 
        }

        while (!temp.isEmpty()) { //Puts last k lements into temp stack 2 
            temp2.push(temp.pop());
        }
        
        for (int i = 0; i < k; i++) { //Puts the last k elements in reverse back into dq 
            dq.push(temp2.pop());
        }

        return dq;
    }

    /**
     * Returns the winer of a game where each iteration player at index current position + offset is removed 
     * @param n Number of players 
     * @param offset Offset to add 
     * @return ID of winner 
     */
    public static int playGame(int n, int offset) {
        SuperDeque<Integer> game = new SuperDeque<>(); //SuperDeque for game 
        int index = 0; //Current index of turn 
        int targetIndex = 0; //Target index of turn 

        if (n <= 0) { //Returns -1 if n is less than 0
            return -1;
        }

        for (int i = 1; i <= n; i++) { //Makes a SuperDeque with all players 
            game.enqueue(i);
        }

        while (n != 1) { //Iterates until theres one player left 
            targetIndex = (index + offset) % getCurrentSize(game); 
            index = targetIndex; 
            game = removeIndex(game, targetIndex);
            n--; 
        }

        return game.dequeue(); 
    }

    /**
     * Removes an index from the SuperDeque 
     * @param dq Target SuperDeque 
     * @param index Target index being removed 
     * @return SuperDeque with index removed 
     */
    private static SuperDeque<Integer> removeIndex(SuperDeque<Integer> dq, int index) {
        SuperDeque<Integer> temp = new SuperDeque<>(); 
        int i = 0; 

        while (!dq.isEmpty()) { //Iterates through the SuperDeque and removes indexed element 
            if (i != index) {
                temp.enqueue(dq.dequeue());
            } else {
                dq.dequeue(); 
            }
            i++; 
        }

        return temp; 
    }

    /**
     * Helper method to check size of SuperDeque 
     * ***Side note why do helper functions need to be private I have to write bs methods like this... 
     * @param dq SuperDeque to check size 
     * @return Size
     */
    private static int getCurrentSize(SuperDeque dq) {
        SuperDeque temp = new SuperDeque<>(); 
        int size = 0; 
        
        while (!dq.isEmpty()) { //Pops all elements of dq to temp stack 
            temp.push(dq.pop());
            size++;
        }

        while (!temp.isEmpty()) { //Puts all elements back 
            dq.push(temp.pop());
        }

        return size; 
    }
}
