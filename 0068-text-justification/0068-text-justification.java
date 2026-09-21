import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int index = 0;
        int n = words.length;

        while (index < n) {
            int totalChars = words[index].length();
            int last = index + 1;

            // Determine how many words can fit in the current line
            while (last < n) {
                if (totalChars + 1 + words[last].length() > maxWidth) break;
                totalChars += 1 + words[last].length();
                last++;
            }

            StringBuilder sb = new StringBuilder();
            int wordCount = last - index;

            // If it's the last line or the line contains only one word -> Left-justify
            if (last == n || wordCount == 1) {
                for (int i = index; i < last; i++) {
                    sb.append(words[i]);
                    if (i < last - 1) {
                        sb.append(" ");
                    }
                }
                // Pad remaining spaces on the right
                while (sb.length() < maxWidth) {
                    sb.append(" ");
                }
            } else {
                // Fully-justify
                int totalWordLength = 0;
                for (int i = index; i < last; i++) {
                    totalWordLength += words[i].length();
                }

                int totalSpaces = maxWidth - totalWordLength;
                int spacesBetweenWords = totalSpaces / (wordCount - 1);
                int extraSpaces = totalSpaces % (wordCount - 1);

                for (int i = index; i < last; i++) {
                    sb.append(words[i]);
                    if (i < last - 1) {
                        // Apply base spaces
                        for (int s = 0; s < spacesBetweenWords; s++) {
                            sb.append(" ");
                        }
                        // Distribute extra spaces to the leftmost slots
                        if (i - index < extraSpaces) {
                            sb.append(" ");
                        }
                    }
                }
            }

            result.add(sb.toString());
            index = last;
        }

        return result;
    }
}