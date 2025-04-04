package Extra_Code.Sort;

public class InsertionSortExample {
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] arr = {9, 5, 1, 4, 3};
        insertionSort(arr);
        System.out.print("Sorted array: ");
        for (int num : arr) System.out.print(num + " ");
        // Output: 1 3 4 5 9
    }
}
