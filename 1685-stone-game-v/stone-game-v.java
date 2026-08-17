class Solution {
    private int[][] memo;
    private int[] prefixSum;
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(memo[i], -1);
        }
        prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + stoneValue[i];
        }
        return dp(0, n - 1);
    }
    private int getSum(int left, int right) {
        return prefixSum[right + 1] - prefixSum[left];
    }
    private int dp(int i, int j) {
        if (i == j) {
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int maxScore = 0;
        for (int k = i; k < j; k++) {
            int leftSum = getSum(i, k);
            int rightSum = getSum(k + 1, j);
            if (leftSum < rightSum) {
                maxScore = Math.max(maxScore, leftSum + dp(i, k));
            } else if (leftSum > rightSum) {
                maxScore = Math.max(maxScore, rightSum + dp(k + 1, j));
            } else {
                maxScore = Math.max(maxScore, leftSum + Math.max(dp(i, k), dp(k + 1, j)));
            }
        }
        memo[i][j] = maxScore;
        return maxScore;
    }
}
