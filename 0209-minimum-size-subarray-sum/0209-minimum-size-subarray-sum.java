class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int min = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int sum = 0;

        while (right < nums.length) {
            sum = sum + nums[right];
            right++;

            while (sum >= target) {
                int last = right - left;
                min = Math.min(last, min);

                sum = sum - nums[left];
                left++;
            }
        }
        if (min == Integer.MAX_VALUE) {
            return 0;
        }
        return min;
    }
}