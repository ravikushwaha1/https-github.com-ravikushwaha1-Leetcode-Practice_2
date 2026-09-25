class Solution {
    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    private int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // Max sum from left and right subtrees; ignore negative contributions
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // Path sum passing through current node as root of the sub-path
        int currentPathSum = node.val + leftGain + rightGain;

        // Update global maximum path sum
        maxSum = Math.max(maxSum, currentPathSum);

        // Return max sum path extending to parent
        return node.val + Math.max(leftGain, rightGain);
    }
}