class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
      int count = 0;
      int currentsum = 0;
      int target = threshold * k;

      for(int i = 0; i < k; i++){
        currentsum = currentsum + arr[i];
      }  
      if(currentsum >= target){
        count++;
      }
      for(int i = k; i < arr.length;i++){
        currentsum = currentsum + arr[i] - arr[i - k];
        if(currentsum >= target){
            count++;
        }
      }
      return count;
    }
}