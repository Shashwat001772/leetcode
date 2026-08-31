class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int[] result = {-1, -1};
        if (head == null || head.next == null || head.next.next == null) {
            return result;
        }
     ListNode prev = head;
     ListNode curr = head.next;
     int position = 1;
        int firstCritical = -1;
        int lastCritical = -1;
        int minDistance = Integer.MAX_VALUE;
        while (curr.next != null) {
            boolean isMax = curr.val > prev.val && curr.val > curr.next.val;
            boolean isMin = curr.val < prev.val && curr.val < curr.next.val;
            if (isMax || isMin) {
                if (firstCritical == -1) {
                    firstCritical = position;
                }
                if (lastCritical != -1) {
                    int distance = position - lastCritical;
                    minDistance = Math.min(minDistance, distance);
                }
                lastCritical = position;
            }
            prev = curr;
            curr = curr.next;
            position++;
        }
        if (firstCritical == lastCritical) {
            return result;
        }
        int maxDistance = lastCritical - firstCritical;
        result[0] = minDistance;
        result[1] = maxDistance;
        return result;
    }
}