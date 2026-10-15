import java.util.List;
import java.util.HashSet;

class Solution {
    public int sumCounts(List<Integer> nums) {
        int totalSum = 0;
        int n = nums.size();
        
        // Generate all possible subarrays
        for (int i = 0; i < n; i++) {
            HashSet<Integer> distinctElements = new HashSet<>();
            for (int j = i; j < n; j++) {
                distinctElements.add(nums.get(j));
                int count = distinctElements.size();
                totalSum += count * count;
            }
        }
        
        return totalSum;
    }
}