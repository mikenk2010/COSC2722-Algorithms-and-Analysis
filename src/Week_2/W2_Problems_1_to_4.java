package Week_2;

import java.util.*;

public class W2_Problems_1_to_4 {

    /**
     * Problem 1:
     * T(N) = 999 * N - sqrt(N)
     * To prove T(N) ∈ O(N^2), we know:
     *   - 999N ∈ O(N)
     *   - sqrt(N) ∈ O(N)
     *   - So, T(N) ∈ O(N) ⊆ O(N^2)
     * Conclusion: T(N) is asymptotically upper bounded by O(N^2)
     */

    // Problem 2.1: Print unique elements - without sorting (O(N^2))
    public static void printUniqueNoSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            boolean seen = false;
            for (int j = 0; j < i; j++) {
                if (arr[i] == arr[j]) {
                    seen = true;
                    break;
                }
            }
            if (!seen) System.out.print(arr[i] + " ");
        }
    }

    // Problem 2.2: Print unique elements - with sorting (O(N log N))
    public static void printUniqueWithSort(int[] arr) {
        Arrays.sort(arr);
        System.out.print(arr[0] + " ");
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) System.out.print(arr[i] + " ");
        }
    }

    // Problem 3: Minimum number of gates required at an airport (O(N log N))
    public static int minGatesRequired(int[] arrival, int[] departure) {
        Arrays.sort(arrival);
        Arrays.sort(departure);
        int gates = 0, maxGates = 0;
        int i = 0, j = 0;
        int n = arrival.length;

        while (i < n) {
            if (arrival[i] < departure[j]) {
                gates++;
                maxGates = Math.max(maxGates, gates);
                i++;
            } else {
                gates--;
                j++;
            }
        }
        return maxGates;
    }

    // Problem 4: Find two numbers whose sum is closest to zero (O(N log N))
    public static int[] closestSumToZero(int[] arr) {
        Arrays.sort(arr);
        int left = 0, right = arr.length - 1;
        int minSum = Integer.MAX_VALUE;
        int[] result = new int[2];

        while (left < right) {
            int sum = arr[left] + arr[right];
            if (Math.abs(sum) < Math.abs(minSum)) {
                minSum = sum;
                result[0] = arr[left];
                result[1] = arr[right];
            }
            if (sum < 0) left++;
            else right--;
        }
        return result;
    }

    // Problem 5: Estimate runtime for O(N^3)
    public static double estimateRuntimeDays(int baseUsers, int baseTimeMs, int targetUsers) {
        double ratio = Math.pow((double) targetUsers / baseUsers, 3);
        double totalMs = baseTimeMs * ratio;
        double totalSeconds = totalMs / 1000.0;
        return totalSeconds / (60 * 60 * 24); // convert to days
    }

    public static void main(String[] args) {
        // Problem 2 - Unique Elements
        System.out.println("\nProblem 2");

        int[] uniqueTest = {12, 29, 30,     6, 8, 10, 11, 6, 10};
        System.out.print("Unique elements (No sort): ");
        printUniqueNoSort(uniqueTest);
        System.out.print("\nUnique elements (With sort): ");
        printUniqueWithSort(uniqueTest);
        System.out.println();

        System.out.println("\nProblem 3");
        // Problem 3 - Minimum Gates Required
        int[] arrivals = {100, 140, 150, 200, 215, 400};
        int[] departures = {110, 300, 220, 230, 315, 600};
        System.out.println("Min gates required: " + minGatesRequired(arrivals, departures));

        System.out.println("\nProblem 4");
        // Problem 4 - Closest Pair Sum to Zero
        int[] arr1 = {-100, 50, -52, 99};
        int[] closest = closestSumToZero(arr1);
        System.out.println("Closest to zero sum: " + closest[0] + " and " + closest[1]);

        System.out.println("\nProblem 5");
        // Problem 5 - Estimate Runtime
        double days = estimateRuntimeDays(1000, 100, 1_000_000);
        System.out.printf("Estimated days for 1M users: %.2f days\n", days);
    }
}