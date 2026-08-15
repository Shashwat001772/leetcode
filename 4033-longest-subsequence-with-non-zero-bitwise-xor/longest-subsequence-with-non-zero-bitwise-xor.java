class Solution {
    public int longestSubsequence(int[] nums) {
        int xor = 0;
        int countZeros = 0;
        int n = nums.length;    
        for (int x : nums) {
            xor ^= x;
            if (x == 0) {
                countZeros++;
            }
        }
        if (xor != 0) {
            return n;
        }
        if (countZeros == n) {
            return 0;
        }
        return n - 1;
    }
}