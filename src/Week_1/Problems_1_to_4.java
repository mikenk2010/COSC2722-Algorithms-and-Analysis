package Week_1;

import java.util.*;

public class Problems_1_to_4 {

    // Problem 1.1: Find the largest number in an array without sorting
    public static int findMax(int[] A) {
        int max = A[0]; // Start with the first element as the maximum
        for (int i = 1; i < A.length; i++) {
            if (A[i] > max) { // Update max if current value is greater
                max = A[i];
            }
        }
        return max;
    }

    // Problem 1.2: Find the second largest number in an array without sorting
    public static int findSecondMax(int[] A) {
        int max = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        for (int value : A) {
            if (value > max) {
                second = max; // Shift the current max to second
                max = value;
            } else if (value > second && value != max) {
                second = value; // Update second max if value is between second and max
            }
        }
        return second;
    }

    // Problem 2: Find the missing number in an array of size N with unique elements from 0 to N
    public static int findMissing(int[] A) {
        int N = A.length;
        int expectedSum = N * (N + 1) / 2; // Sum of numbers from 0 to N
        int actualSum = 0;
        for (int value : A) {
            actualSum += value; // Compute the actual sum of array
        }
        return expectedSum - actualSum; // The missing value is the difference
    }

    // Problem 3: Check if two sequences are permutations of the same set
    public static boolean isPermutation(List<Integer> seq1, List<Integer> seq2) {
        Set<Integer> set1 = new HashSet<>(seq1); // Convert both to sets
        Set<Integer> set2 = new HashSet<>(seq2);
        return set1.equals(set2); // Compare sets for equality
    }

    // Problem 4: Sum all elements within a submatrix defined by top-left and bottom-right corners
    public static int sumRectangle(int[][] matrix, int top, int left, int bottom, int right) {
        int sum = 0;
        for (int i = top; i <= bottom; i++) {
            for (int j = left; j <= right; j++) {
                sum += matrix[i][j]; // Accumulate the values in the defined rectangle
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        // ----------------------------
        // Problem 1 - Max and Second Max
        // ----------------------------
        int[] A = {7, 6, 9, 3, 2, 5};
        System.out.println("Problem 1.1 - Max value: " + findMax(A));           // Expected: 9
        System.out.println("Problem 1.2 - Second Max value: " + findSecondMax(A)); // Expected: 7

        // ----------------------------
        // Problem 2 - Find Missing Number
        // ----------------------------
        int[] B = {0, 3, 2, 4, 1};
        System.out.println("Problem 2 - Missing number: " + findMissing(B));   // Expected: 5

        // ----------------------------
        // Problem 3 - Check if sequences are permutations
        // ----------------------------
        List<Integer> seq1 = Arrays.asList(1, 2, 3, 4);
        List<Integer> seq2 = Arrays.asList(4, 3, 2, 1);
        System.out.println("Problem 3 - Are permutations: " + isPermutation(seq1, seq2)); // Expected: true

        // ----------------------------
        // Problem 4 - Sum of submatrix
        // ----------------------------
        int[][] matrix = {
                {1, 2, 3, 4, 5},
                {8, 6, 9, 1, 3},
                {8, 3, 1, 4, 3},
                {4, 8, 2, 9, 6}
        };
        // Sum of values from row 1 to 2 and col 2 to 3: 9 + 1 + 1 + 4 = 15
        System.out.println("Problem 4 - Rectangle Sum: " + sumRectangle(matrix, 1, 2, 2, 3)); // Expected: 15
    }
}
