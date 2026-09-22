class Solution {

    class Node {
        int product;
        int[] prefix;

        Node(int k) {
            product = 1 % k;
            prefix = new int[k];
        }
    }

    int n, k;
    int[][] prefix;
    int[] product;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;

        prefix = new int[4 * n][k];
        product = new int[4 * n];

        build(1, 0, n - 1, nums);

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];
            update(1, 0, n - 1, index, value);
            Node node = query(1, 0, n - 1, start, n - 1);

            result[i] = node.prefix[x];
        }

        return result;
    }

    private void build(int node, int left, int right, int[] nums) {
        if (left == right) {
            int rem = nums[left] % k;

            product[node] = rem;
            prefix[node][rem] = 1;

            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid, nums);
        build(node * 2 + 1, mid + 1, right, nums);

        merge(node);
    }

    private void update(int node, int left, int right,
                        int index, int value) {

        if (left == right) {
            int rem = value % k;
            product[node] = rem;
            java.util.Arrays.fill(prefix[node], 0);
            prefix[node][rem] = 1;
          return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, right, index, value);
        }

        merge(node);
    }

    private void merge(int node) {
        int leftChild = node * 2;
        int rightChild = node * 2 + 1;

        int leftProduct = product[leftChild];

        product[node] = (int) (
            (long) leftProduct * product[rightChild] % k
        );

        java.util.Arrays.fill(prefix[node], 0);
        for (int r = 0; r < k; r++) {
            prefix[node][r] += prefix[leftChild][r];
        }
        for (int r = 0; r < k; r++) {
            int newRemainder = (int) ((long) leftProduct * r % k);

            prefix[node][newRemainder] += prefix[rightChild][r];
        }
    }

    private Node query(int node, int left, int right,
                       int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft) {
            return new Node(k);
        }

        if (queryLeft <= left && right <= queryRight) {
            Node result = new Node(k);

            result.product = product[node];
            System.arraycopy(
                prefix[node], 0,
                result.prefix, 0, k
            );

            return result;
        }

        int mid = left + (right - left) / 2;

        Node leftResult = query(
            node * 2, left, mid, queryLeft, queryRight
        );

        Node rightResult = query(
            node * 2 + 1, mid + 1, right, queryLeft, queryRight
        );

        return mergeNodes(leftResult, rightResult);
    }

    private Node mergeNodes(Node left, Node right) {
        Node result = new Node(k);

        result.product = (int) (
            (long) left.product * right.product % k
        );
        for (int r = 0; r < k; r++) {
            result.prefix[r] += left.prefix[r];
        }
        for (int r = 0; r < k; r++) {
            int newRemainder = (int) (
                (long) left.product * r % k
            );

            result.prefix[newRemainder] += right.prefix[r];
        }

        return result;
    }
}