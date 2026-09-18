class Solution {
    public int minSwapsCouples(int[] row) {
        int n = row.length;
        int[] pos = new int[n];
        
        // Record the initial index/position of each person
        for (int i = 0; i < n; i++) {
            pos[row[i]] = i;
        }

        int swaps = 0;
        
        // Iterate through couples (seats 0-1, 2-3, etc.)
        for (int i = 0; i < n; i += 2) {
            int firstPerson = row[i];
            // Partner of person x is x^1 (even x -> x+1, odd x -> x-1)
            int expectedPartner = firstPerson ^ 1;

            // If the person sitting next to firstPerson is not their partner
            if (row[i + 1] != expectedPartner) {
                swaps++;
                
                int currentNeighbor = row[i + 1];
                int expectedPartnerIndex = pos[expectedPartner];

                // Swap current neighbor with the expected partner
                row[i + 1] = expectedPartner;
                row[expectedPartnerIndex] = currentNeighbor;

                // Update position mappings after swap
                pos[currentNeighbor] = expectedPartnerIndex;
                pos[expectedPartner] = i + 1;
            }
        }

        return swaps;
    }
}