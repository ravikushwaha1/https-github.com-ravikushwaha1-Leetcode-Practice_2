class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        
        // dp[j] stores the number of subsequences of s that equal t[0...j-1]
        int[] dp = new int[n + 1];
        
        // Base case: An empty t can be formed by 1 empty subsequence of s
        dp[0] = 1;
        
        for (int i = 1; i <= m; i++) {
            char sChar = s.charAt(i - 1);
            // Iterate backwards to use previous step's values without overwriting
            for (int j = n; j >= 1; j--) {
                if (sChar == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        
        return dp[n];
    }
}