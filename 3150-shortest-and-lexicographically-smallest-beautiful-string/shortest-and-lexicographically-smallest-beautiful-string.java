class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        String result = "";
        int minLen = n + 1;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = i; j < n; j++) {
                if (s.charAt(j) == '1') {
                    count++;
                }
                if (count == k) {
                    int currentLen = j - i + 1;
                    String sub = s.substring(i, j + 1);                 
                    if (currentLen < minLen) {
                        minLen = currentLen;
                        result = sub;
                    } else if (currentLen == minLen) {
                        if (result.equals("") || sub.compareTo(result) < 0) {
                            result = sub;
                        }
                    }
                    break; 
                }
            }
        }
        return result;
    }
}
