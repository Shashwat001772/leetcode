import java.util.Arrays;
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;
        int[] diff = new int[n];
        long sum = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            sum += diff[i];
            max = Math.max(max, diff[i]);
        }
        if (sum <= k) {
            return 0;
        }
        int low = 0, high = max;
        while (low < high) {
            int mid = low + (high - low) / 2;
            long required = 0;
            for (int d : diff) {
                if (d > mid) {
                    required += d - mid;
                }
            }
            if (required <= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        long ans = 0;
        long remaining = k;
        for (int d : diff) {
            if (d > low) {
                long reduction = d - low;
                ans += (long) low * low;
                remaining -= reduction;
            } else {
                ans += (long) d * d;
            }
        }
        if (remaining > 0 && low > 0) {
            ans -= remaining * (2L * low - 1);
        }
        return ans;
    }
}