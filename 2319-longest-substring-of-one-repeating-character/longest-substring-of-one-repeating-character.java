class Solution {
    class Node {
        char left, right;
        int prefix, suffix, best, len;
        Node(char left, char right, int prefix, int suffix, int best, int len) {
            this.left = left;
            this.right = right;
            this.prefix = prefix;
            this.suffix = suffix;
            this.best = best;
            this.len = len;
        }
    }
    Node[] tree;
    char[] s;
    public int[] longestRepeating(String str, String queryCharacters,
                                  int[] queryIndices) {

        s = str.toCharArray();
        int n = s.length;
        tree = new Node[4 * n];
        build(1, 0, n - 1);
        int[] answer = new int[queryIndices.length];
        for (int i = 0; i < queryIndices.length; i++) {
            int index = queryIndices[i];
            char newChar = queryCharacters.charAt(i);
            s[index] = newChar;
            update(1, 0, n - 1, index);
            answer[i] = tree[1].best;
        }
        return answer;
    }
    void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(
                s[start],
                s[start],
                1,
                1,
                1,
                1
            );
            return;
        }
        int mid = (start + end) / 2;
        build(node * 2, start, mid);
        build(node * 2 + 1, mid + 1, end);
        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }
    void update(int node, int start, int end, int index) {
        if (start == end) {
            tree[node] = new Node(
                s[index],
                s[index],
                1,
                1,
                1,
                1
            );
            return;
        }
        int mid = (start + end) / 2;
        if (index <= mid) {
            update(node * 2, start, mid, index);
        } else {
            update(node * 2 + 1, mid + 1, end, index);
        }
        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }
    Node merge(Node leftPart, Node rightPart) {
        int len = leftPart.len + rightPart.len;
        int prefix = leftPart.prefix;
        int suffix = rightPart.suffix;
        int best = Math.max(leftPart.best, rightPart.best);
        if (leftPart.right == rightPart.left) {
            best = Math.max(
                best,
                leftPart.suffix + rightPart.prefix
            );
            if (leftPart.prefix == leftPart.len) {
                prefix = leftPart.len + rightPart.prefix;
            }
            if (rightPart.suffix == rightPart.len) {
                suffix = rightPart.len + leftPart.suffix;
            }
        }
        return new Node(
            leftPart.left,
            rightPart.right,
            prefix,
            suffix,
            best,
            len
        );
    }
}