class Solution {
    class SegmentTree {
        int n;
        int k;
        // count[node][r] = number of prefixes starting at segment's left endpoint
        // whose product % k equals r
        int[][] count;
        // prod[node] = total product % k of elements in segment
        int[] prod;

        public SegmentTree(int[] nums, int k) {
            this.n = nums.length;
            this.k = k;
            this.count = new int[4 * n][k];
            this.prod = new int[4 * n];
            build(0, 0, n - 1, nums);
        }

        private void merge(int node, int leftChild, int rightChild) {
            prod[node] = (prod[leftChild] * prod[rightChild]) % k;
            
            // Combine prefix modulo counts
            for (int r = 0; r < k; r++) {
                count[node][r] = count[leftChild][r];
            }
            for (int r = 0; r < k; r++) {
                int newRem = (prod[leftChild] * r) % k;
                count[node][newRem] += count[rightChild][r];
            }
        }

        private void build(int node, int l, int r, int[] nums) {
            if (l == r) {
                int rem = (int) (nums[l] % k);
                prod[node] = rem;
                count[node][rem] = 1;
                return;
            }
            int mid = l + (r - l) / 2;
            build(2 * node + 1, l, mid, nums);
            build(2 * node + 2, mid + 1, r, nums);
            merge(node, 2 * node + 1, 2 * node + 2);
        }

        public void update(int node, int l, int r, int idx, int val) {
            if (l == r) {
                int rem = val % k;
                prod[node] = rem;
                for (int i = 0; i < k; i++) count[node][i] = 0;
                count[node][rem] = 1;
                return;
            }
            int mid = l + (r - l) / 2;
            if (idx <= mid) {
                update(2 * node + 1, l, mid, idx, val);
            } else {
                update(2 * node + 2, mid + 1, r, idx, val);
            }
            merge(node, 2 * node + 1, 2 * node + 2);
        }

        // Returns {prod, count0, count1, ..., count(k-1)} for range [qL, qR]
        public int[] query(int node, int l, int r, int qL, int qR) {
            if (qL <= l && r <= qR) {
                int[] res = new int[k + 1];
                res[0] = prod[node];
                for (int i = 0; i < k; i++) {
                    res[i + 1] = count[node][i];
                }
                return res;
            }

            int mid = l + (r - l) / 2;
            if (qR <= mid) {
                return query(2 * node + 1, l, mid, qL, qR);
            }
            if (qL > mid) {
                return query(2 * node + 2, mid + 1, r, qL, qR);
            }

            int[] leftRes = query(2 * node + 1, l, mid, qL, qR);
            int[] rightRes = query(2 * node + 2, mid + 1, r, qL, qR);

            int[] combined = new int[k + 1];
            combined[0] = (leftRes[0] * rightRes[0]) % k;

            for (int rMod = 0; rMod < k; rMod++) {
                combined[rMod + 1] = leftRes[rMod + 1];
            }
            for (int rMod = 0; rMod < k; rMod++) {
                int newRem = (leftRes[0] * rMod) % k;
                combined[newRem + 1] += rightRes[rMod + 1];
            }

            return combined;
        }
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        SegmentTree st = new SegmentTree(nums, k);
        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // 1. Update element
            st.update(0, 0, n - 1, index, value);

            // 2. Query range [start, n - 1]
            int[] qResult = st.query(0, 0, n - 1, start, n - 1);

            // 3. Extract x-value count
            ans[i] = qResult[x + 1];
        }

        return ans;
    }
}