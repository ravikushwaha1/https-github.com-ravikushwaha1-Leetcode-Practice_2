import java.util.*;

class Solution {
    private List<List<Integer>> adj;
    private int[] tin, tout, depth, head, parent, heavy;
    private int timer;
    private int[] tree;
    private char[] sChars;

    private int dfs(int u, int p, int d) {
        tin[u] = ++timer;
        depth[u] = d;
        parent[u] = p;
        int size = 1;
        int maxSubTree = 0;
        heavy[u] = -1;

        for (int v : adj.get(u)) {
            if (v != p) {
                int subSize = dfs(v, u, d + 1);
                size += subSize;
                if (subSize > maxSubTree) {
                    maxSubTree = subSize;
                    heavy[u] = v;
                }
            }
        }
        tout[u] = timer;
        return size;
    }

    private void decompose(int u, int h) {
        head[u] = h;
        tin[u] = ++timer;
        if (heavy[u] != -1) {
            decompose(heavy[u], h);
        }
        for (int v : adj.get(u)) {
            if (v != parent[u] && v != heavy[u]) {
                decompose(v, v);
            }
        }
    }

    private void updateTree(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node] = val;
            return;
        }
        int mid = (start + end) / 2;
        if (start <= idx && idx <= mid) {
            updateTree(2 * node, start, mid, idx, val);
        } else {
            updateTree(2 * node + 1, mid + 1, end, idx, val);
        }
        tree[node] = tree[2 * node] ^ tree[2 * node + 1];
    }

    private int queryTree(int node, int start, int end, int l, int r) {
        if (r < start || end < l) {
            return 0;
        }
        if (l <= start && end <= r) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        return queryTree(2 * node, start, mid, l, r) ^ queryTree(2 * node + 1, mid + 1, end, l, r);
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        head = new int[n];
        parent = new int[n];
        heavy = new int[n];
        sChars = s.toCharArray();

        timer = 0;
        dfs(0, -1, 0);
        timer = 0;
        decompose(0, 0);

        tree = new int[4 * (n + 1)];
        for (int i = 0; i < n; i++) {
            int mask = 1 << (sChars[i] - 'a');
            updateTree(1, 1, n, tin[i], mask);
        }

        List<Boolean> answer = new ArrayList<>();

        for (String qStr : queries) {
            String[] parts = qStr.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                sChars[u] = c;
                int mask = 1 << (c - 'a');
                updateTree(1, 1, n, tin[u], mask);
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int pathXor = 0;
                while (head[u] != head[v]) {
                    if (depth[head[u]] > depth[head[v]]) {
                        int temp = u;
                        u = v;
                        v = temp;
                    }
                    pathXor ^= queryTree(1, 1, n, tin[head[v]], tin[v]);
                    v = parent[head[v]];
                }
                if (depth[u] > depth[v]) {
                    int temp = u;
                    u = v;
                    v = temp;
                }
                pathXor ^= queryTree(1, 1, n, tin[u], tin[v]);

                if ((pathXor & (pathXor - 1)) == 0) {
                    answer.add(true);
                } else {
                    answer.add(false);
                }
            }
        }

        return answer;
    }
}