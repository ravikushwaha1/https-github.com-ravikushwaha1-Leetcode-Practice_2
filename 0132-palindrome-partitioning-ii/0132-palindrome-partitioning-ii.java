class Solution {
    public int minCut(String s) {
        int n = s.length();
        if (n <= 1) return 0;

        // dp[i] stores the minimum cuts needed for substring s[0...i]
        int[] dp = new int[n];
        
        // isPalindrome[i][j] will be true if s[i...j] is a palindrome
        boolean[][] isPalindrome = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            int minCuts = i; // Max possible cuts needed for s[0...i] is i (all single chars)

            for (int j = 0; j <= i; j++) {
                // Check if substring s[j...i] is a palindrome
                if (s.charAt(i) == s.charAt(j) && (i - j <= 2 || isPalindrome[j + 1][i - 1])) {
                    isPalindrome[j][i] = true;

                    // If s[0...i] is a palindrome, 0 cuts are needed
                    // Otherwise, cut before index j and add 1 cut: dp[j - 1] + 1
                    minCuts = (j == 0) ? 0 : Math.min(minCuts, dp[j - 1] + 1);
                }
            }
            dp[i] = minCuts;
        }

        return dp[n - 1];
    }
}