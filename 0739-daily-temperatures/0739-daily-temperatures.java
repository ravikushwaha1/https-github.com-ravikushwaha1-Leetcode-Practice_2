class Solution {
    public int[] dailyTemperatures(int[] temp) {
        int[] answer = new int[temp.length];

        Stack<Integer> ravi = new Stack<>();
        for (int i = 0; i < temp.length; i++) {

            while (!ravi.isEmpty() && temp[i] > temp[ravi.peek()]) {
                int index = ravi.pop();
                answer[index] = i - index;
            }
            ravi.push(i);
        }
        return answer;

    }
}