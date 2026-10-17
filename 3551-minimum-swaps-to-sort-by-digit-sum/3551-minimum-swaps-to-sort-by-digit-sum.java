import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    
    // Helper function to calculate the sum of digits of a number
    private int getDigitSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    public int minSwaps(int[] nums) {
        int n = nums.length;
        
        // Pair class or simple 2D array to store: {value, digitSum, originalIndex}
        // Using a custom object / wrapper array
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        
        // Sort indices based on custom criteria: digit sum ascending, then value ascending
        Arrays.sort(indices, (a, b) -> {
            int sumA = getDigitSum(nums[a]);
            int sumB = getDigitSum(nums[b]);
            if (sumA != sumB) {
                return Integer.compare(sumA, sumB);
            }
            return Integer.compare(nums[a], nums[b]);
        });
        
        // Find the number of cycles to compute minimum swaps
        boolean[] visited = new boolean[n];
        int cycles = 0;
        
        for (int i = 0; i < n; i++) {
            // If already visited or element is already in the correct position
            if (visited[i] || indices[i] == i) {
                continue;
            }
            
            // Traverse the cycle
            int cycleSize = 0;
            int curr = i;
            while (!visited[curr]) {
                visited[curr] = true;
                curr = indices[curr];
                cycleSize++;
            }
            
            if (cycleSize > 1) {
                cycles += (cycleSize - 1);
            }
        }
        
        return cycles;
    }
}