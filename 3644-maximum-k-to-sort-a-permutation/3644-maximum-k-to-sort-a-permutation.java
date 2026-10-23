class Solution {
    public int sortPermutation(int[] nums) {
        int n = nums.length;
        int k = -1; // Initialize with all bits set (using ~0)
        
        for (int i = 0; i < n; i++) {
            // If the element is not in its correct position
            if (nums[i] != i) {
                if (k == -1) {
                    k = nums[i];
                } else {
                    k &= nums[i];
                }
            }
        }
        
        // If all elements are already sorted
        if (k == -1) {
            return 0;
        }
        
        return k;
    }
}