class Solution {
    public boolean isPalindrome(String s) {
       //String ravi = s.toLowerCase().replaceAll("[\\s\\d]", ""); 
       String ravi = s.toLowerCase().replaceAll("[^a-z0-9]", ""); 
       int  first = 0;
       int  last = ravi.length() - 1;

       while(first < last){
        if(ravi.charAt(first) != ravi.charAt(last)){
        return false;
        }
        first++;
        last--;
       }
      return true;
       
    }
}