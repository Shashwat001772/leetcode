class Solution {
    public int minimumPushes(String word) {
        int[] freq = new int[26];
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }
        Arrays.sort(freq);
        reverse(freq);
        int totalPushes = 0;
        int letterIndex = 0;
        for (int count : freq) {
            if (count == 0) break; 
            int pushesPerLetter = (letterIndex / 8) + 1;
            totalPushes += count * pushesPerLetter;
            letterIndex++;
        }
        return totalPushes;
    }
    private void reverse(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}