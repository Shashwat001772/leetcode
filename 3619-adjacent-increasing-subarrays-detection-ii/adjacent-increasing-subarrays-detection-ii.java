class Solution {
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int prevLen = 0;
        int currLen = 1;
        int ans = 0;
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) > nums.get(i - 1)) {
                currLen++;
            } else {
                ans = Math.max(ans, currLen / 2);
                ans = Math.max(ans, Math.min(prevLen, currLen));
                prevLen = currLen;
                currLen = 1;
            }
        }
        ans = Math.max(ans, currLen / 2);
        ans = Math.max(ans, Math.min(prevLen, currLen));
        return ans;
    }
}