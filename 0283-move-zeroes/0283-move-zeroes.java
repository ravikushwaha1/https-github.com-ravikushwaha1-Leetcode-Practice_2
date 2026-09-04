class Solution {
    public void moveZeroes(int[] nums) {
        ArrayList<Integer>ravi = new ArrayList<>();
        
        int j = 0;
        for(int i = 0; i < nums.length;i++){
            if(nums[i] != 0){
                nums[j] = nums[i];
                j++;
            }
        }
            while(j < nums.length){
                nums[j] = 0;
                j++;
            }
        
       
    }
}