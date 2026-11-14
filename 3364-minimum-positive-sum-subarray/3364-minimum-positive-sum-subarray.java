import java.util.List;

class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int minSum = Integer.MAX_VALUE;
        
        // Iterate over all possible starting indices of the subarray
        for (int i = 0; i < n; i++) {
            int currentSum = 0;
            // Iterate over possible ending indices to satisfy length between l and r
            for (int j = i; j < n; j++) {
                currentSum += nums.get(j);
                int length = j - i + 1;
                
                if (length >= l && length <= r) {
                    if (currentSum > 0) {
                        minSum = Math.min(minSum, currentSum);
                    }
                }
                
                // If the subarray length exceeds r, we can stop the inner loop
                if (length > r) {
                    break;
                }
            }
        }
        
        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}