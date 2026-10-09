class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        
        // Step 1: Every child gets at least 1 candy
        for (int i = 0; i < n; i++) {
            candies[i] = 1;
        }
        
        // Step 2: Left-to-right pass
        // Ensure child gets more candies than left neighbor if rating is higher
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        
        // Step 3: Right-to-left pass
        // Ensure child gets more candies than right neighbor if rating is higher
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }
        
        // Step 4: Sum up the total candies
        int totalCandies = 0;
        for (int candy : candies) {
            totalCandies += candy;
        }
        
        return totalCandies;
    }
}