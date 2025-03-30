package Extra_Code.Search;
import java.util.*;

public class BinarySearchExample {
    public static int binarySearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {2, 4, 6, 8, 10, 12};
        int index = binarySearch(nums, 10);
        System.out.println("Index of 10: " + index); // Output: 4
    }
}
