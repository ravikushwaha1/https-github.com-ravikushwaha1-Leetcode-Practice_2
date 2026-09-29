class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;

        // DP table initialized with size (m + 1) x (n + 1) to handle boundary conditions easily
        int[][] dp = new int[m + 1][n + 1];

        // Fill DP table with infinity
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        // Base cases: setting up imaginary boundary cells past the princess destination
        dp[m][n - 1] = 1;
        dp[m - 1][n] = 1;

        // Fill table bottom-up, right-to-left
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int minHealthNeeded = Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j];
                // Knight's health must be at least 1 at all times
                dp[i][j] = Math.max(1, minHealthNeeded);
            }
        }

        return dp[0][0];
    }
}