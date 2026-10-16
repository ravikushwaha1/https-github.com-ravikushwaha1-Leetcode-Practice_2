import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final int MOD = 1_000_000_007;

    static class Node {
        long sumSq = 0;
        long sumC = 0;
        long lazy = 0;
    }

    private Node[] tree;

    private void apply(int node, int l, int r, long val) {
        long len = (r - l + 1);
        tree[node].sumSq = (tree[node].sumSq + 2 * val % MOD * tree[node].sumC % MOD + val * val % MOD * len) % MOD;
        tree[node].sumC = (tree[node].sumC + val * len) % MOD;
        tree[node].lazy = (tree[node].lazy + val) % MOD;
    }

    private void push(int node, int l, int r) {
        if (tree[node].lazy != 0) {
            int mid = (l + r) / 2;
            apply(2 * node, l, mid, tree[node].lazy);
            apply(2 * node + 1, mid + 1, r, tree[node].lazy);
            tree[node].lazy = 0;
        }
    }

    private void update(int node, int l, int r, int ql, int qr, long val) {
        if (ql > r || qr < l) return;
        if (ql <= l && r <= qr) {
            apply(node, l, r, val);
            return;
        }
        push(node, l, r);
        int mid = (l + r) / 2;
        update(2 * node, l, mid, ql, qr, val);
        update(2 * node + 1, mid + 1, r, ql, qr, val);
        tree[node].sumSq = (tree[2 * node].sumSq + tree[2 * node + 1].sumSq) % MOD;
        tree[node].sumC = (tree[2 * node].sumC + tree[2 * node + 1].sumC) % MOD;
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        tree = new Node[4 * n];
        for (int i = 0; i < 4 * n; i++) {
            tree[i] = new Node();
        }

        Map<Integer, Integer> lastSeen = new HashMap<>();
        long totalSum = 0;

        for (int i = 0; i < n; i++) {
            int prevIndex = lastSeen.getOrDefault(nums[i], -1);
            // Update range [prevIndex + 1, i] by adding 1 to distinct counts
            update(1, 0, n - 1, prevIndex + 1, i, 1);
            lastSeen.put(nums[i], i);

            totalSum = (totalSum + tree[1].sumSq) % MOD;
        }

        return (int) totalSum;
    }
}