class Solution {

    private Integer[][] dp;

    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        dp = new Integer[n][n];

        return findDifference(nums, 0, n - 1) >= 0;
    }
    private int findDifference(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        if (dp[left][right] != null) {
            return dp[left][right];
        }
        int takeLeft = nums[left] - findDifference(nums, left + 1, right);
        int takeRight = nums[right] - findDifference(nums, left, right - 1);
        dp[left][right] = Math.max(takeLeft, takeRight);
        return dp[left][right];
    }
}