class Solution {
    public long maximizeSum(int[] nums, int k) {
        long sum = 0;
        int n = nums.length;
        int[] ravi = new int[n];

        for(int i = 0; i < n;i++){
            sum += nums[i];
            char[] digits = String.valueOf(nums[i]).toCharArray();
            Arrays.sort(digits);
            int max = 0;
            for(int j = digits.length - 1;j >= 0;j--){
                max = max * 10 + (digits[j] - '0');
            }
            ravi[i] = max - nums[i];
        }
        Arrays.sort(ravi);
        for(int i = n - 1; i >= 0 && k > 0;i--){
            if(ravi[i] > 0){
                sum += ravi[i];
                k--;
            }
        }
        return sum;
    }
}