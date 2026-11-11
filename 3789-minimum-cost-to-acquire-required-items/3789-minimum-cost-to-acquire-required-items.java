class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long c1 = cost1;
        long c2 = cost2;
        long cB = costBoth;
        long n1 = need1;
        long n2 = need2;
        
        long totalCost = 0;
        
        // 1. For the overlapping requirements (min(need1, need2))
        long common = Math.min(n1, n2);
        totalCost += common * Math.min(cB, c1 + c2);
        n1 -= common;
        n2 -= common;
        
        // 2. For the remaining type 1 requirement (if any)
        if (n1 > 0) {
            totalCost += n1 * Math.min(cB, c1);
        }
        
        // 3. For the remaining type 2 requirement (if any)
        if (n2 > 0) {
            totalCost += n2 * Math.min(cB, c2);
        }
        
        return totalCost;
    }
}