package Extra_Code.Search;

public class LinearSearchExample {
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {7, 5, 9, 2};
        System.out.println("Found 9 at index: " + linearSearch(arr, 9)); // Output: 2
    }
}

