class Solution {
    public int calPoints(String[] operations) {
        Stack<Integer> ravi = new Stack<>();

        for (int i = 0; i < operations.length; i++) {
            String op = operations[i];
            if (!op.equals("+") && !op.equals("D") && !op.equals("C")) {
                int nums = Integer.parseInt(op);

                ravi.add(nums);

            } else if (op.equals("+")) {
                int last = ravi.peek();
                int secondlast = ravi.get(ravi.size() - 2);
                int sum = last + secondlast;
                ravi.add(sum);

            } else if (op.equals("D")) {
                int delast = ravi.peek();
                int ans = delast * 2;
                ravi.add(ans);

            } else if (op.equals("C")) {
                ravi.pop();
            }
        }
        int total = 0;
        for (int j = 0; j < ravi.size(); j++) {
           total += ravi.get(j);

        }

        return total;
    }
}