import java.util.*;
class Solution {
    static final long CAP = 2_000_000L;
    public String smallestPalindrome(String s, int k) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) cnt[c - 'a']++;
        char mid = 0;
        boolean hasMid = false;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] % 2 == 1) {
                mid = (char) ('a' + i);
                hasMid = true;
                cnt[i]--;
                break;
            }
        }
        int[] half = new int[26];
        int halfLen = 0;
        for (int i = 0; i < 26; i++) {
            half[i] = cnt[i] / 2;
            halfLen += half[i];
        }
        long total = countArrangements(half, halfLen);
        if (k > total) return "";
        StringBuilder result = new StringBuilder();
        int remainingLen = halfLen;
        for (int pos = 0; pos < halfLen; pos++) {
            for (int c = 0; c < 26; c++) {
                if (half[c] == 0) continue;
                half[c]--;
                long ways = countArrangements(half, remainingLen - 1);
                if (k <= ways) {
                    result.append((char) ('a' + c));
                    remainingLen--;
                    break;
                } else {
                    k -= ways;
                    half[c]++;
                }
            }
        }
        String halfStr = result.toString();
        StringBuilder ans = new StringBuilder();
        ans.append(halfStr);
        if (hasMid) ans.append(mid);
        ans.append(new StringBuilder(halfStr).reverse());
        return ans.toString();
    }
    private long countArrangements(int[] counts, int n) {
        long totalWays = 1;
        int remaining = n;
        for (int i = 0; i < 26; i++) {
            int v = counts[i];
            if (v == 0) continue;
            long num = 1;
            for (int j = 1; j <= v; j++) {
                num = num * (remaining - v + j) / j;
                if (num > CAP) { num = CAP; break; }
            }
            totalWays *= num;
            if (totalWays > CAP) totalWays = CAP;
            remaining -= v;
        }
        return totalWays;
    }
}