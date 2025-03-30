package Midterm_Test_SAMPLE;

public class Problem3_Sorted_Array {
    int position(int[] A, int X) {
        int N = A.length;
        for (int i = 0; i < N; i++) {
            if (X < A[i]) return i;
        }
        return N;
    }

    // Improved Approach (Binary Search):
    // Time Complexity: O(log N) due to binary search.
    int binaryInsertPosition(int[] A, int X) {
        int low = 0, high = A.length - 1, ans = A.length;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (X < A[mid]) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}
