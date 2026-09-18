class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];

        // Base case: empty string matches empty pattern
        dp[0][0] = true;

        // Deals with patterns like a*, a*b*, or a*b*c* matching an empty string
        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char pChar = p.charAt(j - 1);

                if (pChar == '*') {
                    // Option 1: Treat '*' as matching 0 occurrences of preceding character
                    dp[i][j] = dp[i][j - 2];

                    // Option 2: Treat '*' as matching 1 or more occurrences
                    char prevChar = p.charAt(j - 2);
                    if (prevChar == '.' || prevChar == s.charAt(i - 1)) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else {
                    // Match current single character or '.'
                    if (pChar == '.' || pChar == s.charAt(i - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }

        return dp[m][n];
    }
}