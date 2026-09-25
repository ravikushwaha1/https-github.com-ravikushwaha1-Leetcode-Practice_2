class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                // Minimum must be in the right unsorted part
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                // Minimum is at mid or in the left part
                right = mid;
            } else {
              
                right--;
            }
        }

        return nums[left];
    }
}