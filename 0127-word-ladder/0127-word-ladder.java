import java.util.*;

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        
        // If the endWord is not in the dictionary, no valid transformation exists.
        if (!set.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        
        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String currentWord = queue.poll();

                // Check if we reached the target word
                if (currentWord.equals(endWord)) {
                    return level;
                }

                // Try replacing each character in currentWord with 'a' through 'z'
                char[] wordChars = currentWord.toCharArray();
                for (int j = 0; j < wordChars.length; j++) {
                    char originalChar = wordChars[j];

                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) continue;

                        wordChars[j] = c;
                        String nextWord = String.valueOf(wordChars);

                        if (set.contains(nextWord)) {
                            queue.add(nextWord);
                            set.remove(nextWord); // Mark as visited to prevent revisiting
                        }
                    }

                    wordChars[j] = originalChar; // Restore character
                }
            }
            level++;
        }

        return 0;
    }
}