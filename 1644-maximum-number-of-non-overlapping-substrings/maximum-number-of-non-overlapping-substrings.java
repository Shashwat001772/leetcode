import java.util.ArrayList;
import java.util.List;
class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] left = new int[26];
        int[] right = new int[26];      
        for (int i = 0; i < 26; i++) {
            left[i] = n;
            right[i] = -1;
        }
        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            left[idx] = Math.min(left[idx], i);
            right[idx] = Math.max(right[idx], i);
        }
        List<String> result = new ArrayList<>();
        int lastRight = -1; 
        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            if (i == left[idx]) {
                int end = getValidEnd(s, i, left, right);
                if (end != -1) {
                    if (i > lastRight) {
                        result.add(s.substring(i, end + 1));
                    } else {
                        result.set(result.size() - 1, s.substring(i, end + 1));
                    }
                    lastRight = end;
                }
            }
        }
        return result;
    }
    private int getValidEnd(String s, int start, int[] left, int[] right) {
        int end = right[s.charAt(start) - 'a'];
        for (int i = start; i <= end; i++) {
            int idx = s.charAt(i) - 'a';
            if (left[idx] < start) {
                return -1;
            }
            end = Math.max(end, right[idx]);
        }
        return end;
    }
}
