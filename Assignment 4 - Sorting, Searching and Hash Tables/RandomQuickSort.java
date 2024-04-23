import java.util.*; 

public class RandomQuickSort {
    /**
     * Sorts array using QuickSort
     * @param nums aray to be sorted
     * @param first first index
     * @param last last index
     */
    public static <T extends Comparable <? super T>> void quickSort(T[] nums, int first, int last) {
        if (nums.length == 0 || first < 0 || last >= nums.length) {
            return; 
        }

        if (first < last) {
            int pivotIndex = partition(nums, first, last); 
            quickSort(nums, first, pivotIndex - 1); //Sorts Smaller
            quickSort(nums, pivotIndex + 1, last); //Sorts Larger
        } 
    }

    /**
     * Partitions array into two parts
     * @param nums array to be partitioned
     * @param first starting index 
     * @param last ending index
     * @return partition border
     */
    public static <T extends Comparable <? super T>> int partition(T[] nums, int first, int last) {
        Random random = new Random();
    
        int pivotIndex = random.nextInt(last - first + 1) + first; //Random pivot index
        int partitionBorder = first - 1; //Border between low and high 
        T pivot = nums[pivotIndex]; //Pivot value
            
        swap(nums, pivotIndex, last); //Swaps pivot element to last index 
    
        for (int i = first; i <= last - 1; i++) { //Iterates through the array and swaps elements by comparing them to pivot 
            if (nums[i].compareTo(pivot) < 0) {
                partitionBorder++; 
                swap(nums, i, partitionBorder);
            }
        }
            
        swap(nums, partitionBorder + 1, last); //Places pivot element between the smaller and larger partition
        return(partitionBorder + 1);
    }
    
    /**
     * Swaps two elements in an array
     * @param nums array
     * @param first starting index 
     * @param last ending index
     */
    public static <T extends Comparable <? super T>> void swap(T[] nums, int first, int last) {
        T temp = nums[first];
        nums[first] = nums[last];
        nums[last] = temp; 
    }
}