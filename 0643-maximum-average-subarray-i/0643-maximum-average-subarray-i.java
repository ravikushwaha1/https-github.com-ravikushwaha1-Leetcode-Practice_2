class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
       
        for(int i = 0; i < k;i++){
            sum = sum + nums[i];
          
        }
        int maxsum = sum;

        int startindex = 0;
        int endindex = k;

       while(endindex < nums.length){

        // Remove the previous element
        sum = sum - nums[startindex];
        startindex++;
        
        // Add the new element 
        sum  = sum + nums[endindex];
        endindex++;

        maxsum = Math.max(sum ,maxsum);
       }
       return (double) maxsum / k;
    }
}