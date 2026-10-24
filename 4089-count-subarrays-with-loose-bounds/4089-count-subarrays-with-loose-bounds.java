class Solution {
    public long countSubarrays(int[] nums, int mink, int maxk) {
        long  count = 0;
        int start = 0;
        for(int i = 0; i < nums.length;i++){
            if(nums[i] < mink || nums[i] > maxk){
                start = i + 1;
                
            }else{
                count += i - start + 1;
            }
        }
        return count;
    }
}