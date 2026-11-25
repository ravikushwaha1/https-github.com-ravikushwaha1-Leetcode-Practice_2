class Solution {
    public boolean check(int[] nums) {
        int len = nums.length - 1;

        int deviation = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i < len  && nums[i] > nums[i + 1]) {
                deviation++;
            } else {
                if (i == len  && nums[len] > nums[0]) {
                    deviation++;
                }
            }
        }
        if (deviation > 1) {
            return false;
        }
        return true;

    }
}