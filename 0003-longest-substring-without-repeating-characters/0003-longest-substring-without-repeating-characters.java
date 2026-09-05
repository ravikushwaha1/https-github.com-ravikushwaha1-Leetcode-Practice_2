class Solution {
    public int lengthOfLongestSubstring(String s) {
       Set<Character>ravi = new HashSet<>();
        
        int maxlength = 0;
        int left = 0;
        for(int right = 0; right < s.length();right++){

            while(ravi.contains(s.charAt(right))){
                ravi.remove(s.charAt(left));
                left++;
            }
            ravi.add(s.charAt(right));
            maxlength = Math.max(maxlength, right - left + 1);

        }
        return maxlength;

    }

}