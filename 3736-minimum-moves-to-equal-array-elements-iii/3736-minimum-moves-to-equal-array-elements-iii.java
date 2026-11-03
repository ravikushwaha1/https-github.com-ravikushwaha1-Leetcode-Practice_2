class Solution {
    public int minMoves(int[] nums) {
        // Find the maximum value in the array
        int maxVal = nums[0];
        for (int num : nums) {
            if (num > maxVal) {
                maxVal = num;
            }
        }
        
        // Sum up the differences to find the total moves
        int totalMoves = 0;
        for (int num : nums) {
            totalMoves += (maxVal - num);
        }
        
        return totalMoves;
    }
}