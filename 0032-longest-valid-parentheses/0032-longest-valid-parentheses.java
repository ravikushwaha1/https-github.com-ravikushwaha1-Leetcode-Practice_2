import java.util.Stack;

class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        // Base index to help calculate length when valid substring starts at index 0
        stack.push(-1);
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                // Push index of open parenthesis
                stack.push(i);
            } else {
                // Pop the matching '(' or the previous boundary
                stack.pop();
                
                if (stack.isEmpty()) {
                    // If stack is empty, this ')' acts as a new boundary
                    stack.push(i);
                } else {
                    // Calculate length of the current valid substring
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }

        return maxLen;
    }
}