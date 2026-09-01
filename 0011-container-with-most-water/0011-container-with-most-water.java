class Solution {
    public int maxArea(int[] height) {
       int maxweight = 0;
       int left = 0;
       int right = height.length - 1;
       while(left < right){
        int weight = right - left;
        int ht = Math.min(height[left], height[right]);
        int current = weight * ht;
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