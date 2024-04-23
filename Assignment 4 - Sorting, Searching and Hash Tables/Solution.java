import java.util.*;

public class Solution {
    
    private HashMap<Double, int[]> distanceMap = new HashMap<Double, int[]>();

    /**
     * Finds the k points closest to the point (x = 233, y = 233)
     * @param points Coordinates on the XY plane
     * @param k Integer representing the number of points to find
     * @return Array of k points closest to (233, 233)
     */
    public int[][] kClosest(int[][] points, int k) {

        if (k > points.length || k < 0) {
            throw new IllegalArgumentException("Invalid k value");
        }

        double[] distances = new double[points.length];

        for (int i = 0; i < points.length; i++) {
            distanceMap.put(calculateDistance(points[i][0], points[i][1]), points[i]);
            distances[i] = calculateDistance(points[i][0], points[i][1]);
        }

        quickSort(distances, 0, distances.length - 1);

        int[][] result = new int[k][2];

        for (int i = 0; i < k; i++) {
            result[i] = distanceMap.get(distances[i]);
        }

        return result; 
    }

    /**
     * Calculates the distance between a point and (233, 233)
     * @param x X coordinate
     * @param y Y coordinate
     * @return Distance between the point and (233, 233)
     */
    public double calculateDistance(int x, int y) {
        return Math.sqrt(Math.pow(x - 233, 2) + Math.pow(y - 233, 2));
    }

    private void quickSort(double[] nums, int first, int last) {
        if (first < last) {
            int pivotIndex = partition(nums, first, last);
            quickSort(nums, first, pivotIndex - 1);
            quickSort(nums, pivotIndex + 1, last);
        }
    }

    private int partition(double[] nums, int first, int last) {
        int pivotIndex = last;
        int partitionBorder = first - 1;
        double pivot = nums[pivotIndex];

        swap(nums, pivotIndex, last);

        for (int i = first; i <= last - 1; i++) {
            if (nums[i] < pivot) {
                partitionBorder++;
                swap(nums, i, partitionBorder);
            }
        }

        swap(nums, partitionBorder + 1, last);
        return partitionBorder + 1;
    }

    private void swap(double[] nums, int first, int last) {
        double temp = nums[first];
        nums[first] = nums[last];
        nums[last] = temp;
    }
}
