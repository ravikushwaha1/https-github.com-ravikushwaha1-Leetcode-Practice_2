import java.util.HashMap;
import java.util.Map;

public class Solution {
    private Map<String, Boolean> memo = new HashMap<>();

    public boolean isScramble(String s1, String s2) {
        // Base Cases
        if (s1.equals(s2)) {
            return true;
        }
        if (s1.length() != s2.length()) {
            return false;
        }

        // Memoization check
        String key = s1 + "_" + s2;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // Optimization: Quick check using character frequencies
        int[] letters = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            letters[s1.charAt(i) - 'a']++;
            letters[s2.charAt(i) - 'a']--;
        }
        for (int count : letters) {
            if (count != 0) {
                memo.put(key, false);
                return false;
            }
        }

        int n = s1.length();
        // Try all split points from length 1 to n - 1
        for (int i = 1; i < n; i++) {
            // Case 1: Substrings are NOT swapped
            // s1[0...i-1] matches s2[0...i-1] AND s1[i...n-1] matches s2[i...n-1]
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) &&
                isScramble(s1.substring(i), s2.substring(i))) {
                memo.put(key, true);
                return true;
            }

            // Case 2: Substrings ARE swapped
            // s1[0...i-1] matches s2[n-i...n-1] AND s1[i...n-1] matches s2[0...n-i-1]
            if (isScramble(s1.substring(0, i), s2.substring(n - i)) &&
                isScramble(s1.substring(i), s2.substring(0, n - i))) {
                memo.put(key, true);
                return true;
            }
        }

        memo.put(key, false);
        return false;
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Test Cases
        String s1 = "great", s2 = "rgeat";
        System.out.println("Input: s1 = \"" + s1 + "\", s2 = \"" + s2 + "\"");
        System.out.println("Output: " + solver.isScramble(s1, s2)); // Expected: true

        String s3 = "abcde", s4 = "caebd";
        System.out.println("\nInput: s1 = \"" + s3 + "\", s2 = \"" + s4 + "\"");
        System.out.println("Output: " + solver.isScramble(s3, s4)); // Expected: false
    }
}