class Solution {
    public int maxArea(int[] height) {
       int maxweight = 0;
    
       //Left Pointer 
       int left = 0;

       //Right Pointer
       int right = height.length - 1;

       while(left < right){
        int weight = right - left;


        int ht = Math.min(height[left], height[right]);
        int current = weight * ht;

        //According to question we are finding the Max weight
        maxweight = Math.max(maxweight , current);

        
        if(height[left] < height[right]){
            left++;
        }else{
            right--;
        }
        
       }
       return maxweight;
    }
}