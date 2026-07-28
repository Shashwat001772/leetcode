class Solution {
    public String smallestPalindrome(String s) {
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }
        StringBuilder firstHalf = new StringBuilder();
        String middle = "";
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                middle = String.valueOf((char) (i + 'a'));
            }
            for (int j = 0; j < freq[i] / 2; j++) {
                firstHalf.append((char) (i + 'a'));
            }
        }
        String secondHalf = new StringBuilder(firstHalf).reverse().toString();
        return firstHalf.toString() + middle + secondHalf;
    }
}