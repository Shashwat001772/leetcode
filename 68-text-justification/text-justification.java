import java.util.*;
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int n = words.length;
        int i = 0;
        while (i < n) {
            int j = i;
            int letters = 0;
            while (j < n &&
                   letters + words[j].length() + (j - i) <= maxWidth) {
                letters += words[j].length();
                j++;
            }
            int gaps = j - i - 1;
            StringBuilder line = new StringBuilder();
            if (j == n || gaps == 0) {
                for (int k = i; k < j; k++) {
                    line.append(words[k]);

                    if (k < j - 1) {
                        line.append(' ');
                    }
                }
                while (line.length() < maxWidth) {
                    line.append(' ');
                }
            } else {
                int totalSpaces = maxWidth - letters;
                int spaces = totalSpaces / gaps;
                int extraSpaces = totalSpaces % gaps;
                for (int k = i; k < j; k++) {
                    line.append(words[k]);
                    if (k < j - 1) {
                        int count = spaces;
                        if (extraSpaces > 0) {
                            count++;
                            extraSpaces--;
                        }
                        for (int s = 0; s < count; s++) {
                            line.append(' ');
                        }
                    }
                }
            }
            result.add(line.toString());
            i = j;
        }
        return result;
    }
}