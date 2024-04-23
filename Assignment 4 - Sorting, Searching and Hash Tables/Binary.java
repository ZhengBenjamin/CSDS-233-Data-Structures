public class Binary {

    /**
     * Finds the minimum element in a rotated sorted array
     * @param nums array to be searched
     * @return minimum element
     */
    public int findMin(int[] nums) {
        if (nums.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }

        int start = 0;
        int end = nums.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2; //Midpoints
            
            if (nums[mid] < nums[end]) { //Element is left of mid 
                end = mid; 
            } else { //Element is right of mid
                start = mid + 1; 
            }
        }

        return nums[start];
    }

}

