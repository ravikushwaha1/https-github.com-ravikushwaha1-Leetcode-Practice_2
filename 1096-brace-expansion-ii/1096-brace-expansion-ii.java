import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        // Stack holds sets of strings and operators ('+' for concatenation, ',' for union, '(' for groups)
        Stack<Object> stack = new Stack<>();
        
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            
            if (c == '{') {
                // If there was a preceding set or closing brace, insert implicit concatenation '+'
                if (i > 0 && (expression.charAt(i - 1) == '}' || Character.isLetter(expression.charAt(i - 1)))) {
                    pushOp(stack, '+');
                }
                stack.push('{');
            } else if (c == ',') {
                stack.push(',');
            } else if (c == '}') {
                // Evaluate inside the braces until matching '{'
                List<Set<String>> group = new ArrayList<>();
                while (!stack.peek().equals('{')) {
                    Object top = stack.pop();
                    if (top instanceof Set) {
                        group.add((Set<String>) top);
                    }
                }
                stack.pop(); // Pop '{'
                
                // Perform union on all expressions separated by ',' inside this brace
                Set<String> unionSet = new TreeSet<>();
                for (Set<String> set : group) {
                    unionSet.addAll(set);
                }
                pushSet(stack, unionSet);
            } else if (Character.isLetter(c)) {
                // Check if implicit concatenation '+' is needed
                if (i > 0 && (expression.charAt(i - 1) == '}' || Character.isLetter(expression.charAt(i - 1)))) {
                    pushOp(stack, '+');
                }
                
                Set<String> set = new TreeSet<>();
                set.add(String.valueOf(c));
                pushSet(stack, set);
            }
        }
        
        // Final evaluation of top-level expressions (union of all remaining sets separated by commas)
        Set<String> resultSet = new TreeSet<>();
        while (!stack.isEmpty()) {
            Object top = stack.pop();
            if (top instanceof Set) {
                resultSet.addAll((Set<String>) top);
            }
        }
        
        return new ArrayList<>(resultSet);
    }
    
    // Pushes operator maintaining '+' precedence over ','
    private void pushOp(Stack<Object> stack, char op) {
        if (op == ',') {
            stack.push(',');
        } else if (op == '+') {
            stack.push('+');
        }
    }

    // Pushes a set to stack, automatically processing top-level concatenation if '+' is on stack
    private void pushSet(Stack<Object> stack, Set<String> set) {
        if (!stack.isEmpty() && stack.peek().equals('+')) {
            stack.pop(); // Pop '+'
            Set<String> prevSet = (Set<String>) stack.pop();
            Set<String> concatenated = new TreeSet<>();
            for (String s1 : prevSet) {
                for (String s2 : set) {
                    concatenated.add(s1 + s2);
                }
            }
            pushSet(stack, concatenated);
        } else {
            stack.push(set);
        }
    }
}