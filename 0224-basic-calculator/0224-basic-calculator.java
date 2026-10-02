import java.util.Stack;

class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int currentResult = 0;
        int currentNumber = 0;
        int sign = 1; // 1 for '+', -1 for '-'

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                // Form multi-digit numbers
                currentNumber = currentNumber * 10 + (ch - '0');
            } else if (ch == '+') {
                // Evaluate the expression to the left
                currentResult += sign * currentNumber;
                currentNumber = 0;
                sign = 1;
            } else if (ch == '-') {
                // Evaluate the expression to the left
                currentResult += sign * currentNumber;
                currentNumber = 0;
                sign = -1;
            } else if (ch == '(') {
                // Save the current result and sign onto the stack
                stack.push(currentResult);
                stack.push(sign);
                
                // Reset for the new sub-expression inside parenthesis
                currentResult = 0;
                sign = 1;
            } else if (ch == ')') {
                // Evaluate the last number inside the parenthesis
                currentResult += sign * currentNumber;
                currentNumber = 0;

                // Multiply by the sign before the parenthesis
                currentResult *= stack.pop();
                
                // Add the result evaluated before entering the parenthesis
                currentResult += stack.pop();
            }
        }

        // Add any remaining number at the end
        return currentResult + (sign * currentNumber);
    }
}