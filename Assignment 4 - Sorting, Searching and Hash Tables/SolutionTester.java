import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Random; 
import org.junit.Test;
import org.junit.Before; 

public class SolutionTester {

    public int[][] largeRandom;

    @Before 
    public void setUp() {
        Random random = new Random();

        largeRandom = new int[10000000][2];
        for (int i = 0; i < largeRandom.length; i++) {
            largeRandom[i][0] = random.nextInt(500);
            largeRandom[i][1] = random.nextInt(500);
        }
    }

    @Test
    public void testKClosestRandom() {
        Solution solution = new Solution();
        int[][] result = solution.kClosest(largeRandom, 10000000);

        double[] sortedDistances = calculateSortedDistances(largeRandom); //Sorted distance of all points 
        double[] distances = calculateDistance(result); //Distances of KClosest output 

        for (int i = 0; i < result.length; i++) { //Checks if the distances are the same
            assertTrue(distances[i] == sortedDistances[i]);
        }
    }

    @Test
    public void testKClosestEmpty() {
        Solution solution = new Solution();
        int[][] points = {};
        int k = 0;
        int[][] expected = {};
        int[][] result = solution.kClosest(points, k);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testKClosestSinglePoint() {
        Solution solution = new Solution();
        int[][] points = {{100, 100}};
        int k = 1;
        int[][] expected = {{100, 100}};
        int[][] result = solution.kClosest(points, k);
        assertArrayEquals(expected, result);
    }

    @Test 
    public void testKClosestInvalidK() {
        Solution solution = new Solution();
        int[][] points = {{100, 100}};
        
        assertThrows(IllegalArgumentException.class, () -> {
            solution.kClosest(points, 2);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            solution.kClosest(points, -1);
        });
    }

    /**
     * Helper method that makes an array of the sorted distances from kClosest method
     * @param points array of points inputed 
     * @return array of distances
     */
    private double[] calculateSortedDistances(int[][] points) {
        Solution solution = new Solution();
        int[][] result = solution.kClosest(points, points.length);
        double[] distances = new double[points.length];
        
        for (int i = 0; i < points.length; i++) {
            distances[i] = solution.calculateDistance(result[i][0], result[i][1]);
        }

        return distances;
    }

    /**
     * Helper methods that makes an array of distances
     * @param points array of points inputed 
     * @return array of distances
     */
    private double[] calculateDistance(int[][] points) {
        double[] distances = new double[points.length];
        for (int i = 0; i < points.length; i++) {
            distances[i] = Math.sqrt(Math.pow(points[i][0] - 233, 2) + Math.pow(points[i][1] - 233, 2));
        }
        return distances;
    }
}
