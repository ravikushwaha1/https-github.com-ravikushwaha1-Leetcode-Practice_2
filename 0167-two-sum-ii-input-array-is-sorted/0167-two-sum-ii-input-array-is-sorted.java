class Solution {
    public int[] twoSum(int[] nums, int target) {
        int low = 0;
        int heigh = nums.length - 1;

        while (low < heigh) {
            int current = nums[low] + nums[heigh];

            if (current == target) {
                return new int[] { low + 1, heigh + 1 };

            } else if (current < target) {

                low++;
            } else {
                heigh--;
            }

        }
        return new int[] { -1, -1 };
    }
}