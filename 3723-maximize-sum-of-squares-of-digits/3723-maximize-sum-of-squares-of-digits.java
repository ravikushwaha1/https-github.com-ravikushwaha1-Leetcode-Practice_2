class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // If the maximum possible sum with 'num' digits is less than 'sum', 
        // or if 'sum' is greater than what 9s can provide, it's impossible.
        if (sum > 9L * num || sum < 0) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder(num);
        for (int i = 0; i < num; i++) {
            int digit = Math.min(9, sum);
            sb.append((char) ('0' + digit));
            sum -= digit;
        }
        
        // If there is any remaining sum that couldn't be placed, return ""
        if (sum > 0) {
            return "";
        }
        
        return sb.toString();
    }
}