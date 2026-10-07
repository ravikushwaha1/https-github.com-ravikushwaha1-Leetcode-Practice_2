import java.util.*;

public class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> result = new ArrayList<>();
        // Sort words by length in ascending order
        Arrays.sort(words, (a, b) -> Integer.compare(a.length(), b.length()));
        
        Set<String> wordSet = new HashSet<>();
        
        for (String word : words) {
            if (word.isEmpty()) continue;
            // Check if the current word can be formed by shorter words in wordSet
            if (canForm(word, wordSet, new HashMap<>())) {
                result.add(word);
            }
            // Add the current word to the set for future words to reference
            wordSet.add(word);
        }
        
        return result;
    }

    private boolean canForm(String word, Set<String> wordSet, Map<String, Boolean> memo) {
        if (memo.containsKey(word)) {
            return memo.get(word);
        }

        for (int i = 1; i < word.length(); i++) {
            String prefix = word.substring(0, i);
            String suffix = word.substring(i);
            
            if (wordSet.contains(prefix)) {
                if (wordSet.contains(suffix) || canForm(suffix, wordSet, memo)) {
                    memo.put(word, true);
                    return true;
                }
            }
        }
        
        memo.put(word, false);
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Sample Test Case 1
        String[] words1 = {"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"};
        System.out.println("Output 1: " + solution.findAllConcatenatedWordsInADict(words1));

        // Sample Test Case 2
        String[] words2 = {"cat", "dog", "catdog"};
        System.out.println("Output 2: " + solution.findAllConcatenatedWordsInADict(words2));
    }
}