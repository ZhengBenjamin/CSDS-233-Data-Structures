import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class QueueStackProblemsTester {
    
    @Test
    public void testEvaluatePostFix() {
        assertEquals(14, QueueStackProblems.evaluatePostFix("5 1 2 + * 1 -"));
        assertEquals(3, QueueStackProblems.evaluatePostFix("4 1 -"));
        assertEquals(6, QueueStackProblems.evaluatePostFix("2 3 *"));
        assertEquals(3, QueueStackProblems.evaluatePostFix("6 2 /"));
        assertEquals(53, QueueStackProblems.evaluatePostFix("27 3 9 * + 1 -"));
    }

    @Test
    public void testReverseWords() {
        String testString1 = "Hello";
        assertEquals("olleH", QueueStackProblems.reverseWords(testString1)); //Tests reverse single word 

        String testString2 = "Hello World";
        assertEquals("olleH dlroW", QueueStackProblems.reverseWords(testString2)); //Tests reverse two words 

        String testString3 = "123456789011121314151617181920"; 
        assertEquals("029181716151413121110987654321", QueueStackProblems.reverseWords(testString3)); //Tests reverse long string 

        String testString4 = "Tamanna is a bully. She is very mean!";
        assertEquals("annamaT si a .yllub ehS si yrev !naem", QueueStackProblems.reverseWords(testString4)); //Tests string with multiple words 

        String testString5 = " ";
        assertEquals(" ", QueueStackProblems.reverseWords(testString5)); //Tests empty string
    }

    @Test
    public void testReverseK() {
        SuperDeque<Integer> empty = new SuperDeque<>(); 
        SuperDeque<Integer> integers = new SuperDeque<>();
        
        for (int i = 10; i > 0; i--) {
            integers.push(i);
        }

        QueueStackProblems.reverseK(integers, 5); 
        assertEquals("5, 4, 3, 2, 1, 6, 7, 8, 9, 10", integers.toString()); //Tests on integer SuperDeque 

        QueueStackProblems.reverseK(empty, 0); 
        assertEquals("", empty.toString()); //Tests on empty SuperDeque 
        
    }

    @Test
    public void testPlayGame() {
        assertEquals(2, QueueStackProblems.playGame(2, 16));
        assertEquals(19, QueueStackProblems.playGame(20, 24));
        assertEquals(13, QueueStackProblems.playGame(24, 74));
        assertEquals(21, QueueStackProblems.playGame(24, 342));
    }
}
