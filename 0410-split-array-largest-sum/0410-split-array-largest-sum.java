class Solution {
    public int splitArray(int[] nums, int k) {
        int maxVal = 0;
        int totalSum = 0;

        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
            totalSum += num;
        }

        int low = maxVal;
        int high = totalSum;
        int result = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canSplit(nums, k, mid)) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }

    private boolean canSplit(int[] nums, int k, int maxAllowedSum) {
        int count = 1;
        int currentSum = 0;

        for (int num : nums) {
            if (currentSum + num > maxAllowedSum) {
                count++;
                currentSum = num;
            } else {
                currentSum += num;
            }
        }

        return count <= k;
    }
}