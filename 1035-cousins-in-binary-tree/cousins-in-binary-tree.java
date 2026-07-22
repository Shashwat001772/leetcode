class Solution {
    HashMap<Integer, Integer> depth = new HashMap<>();
    HashMap<Integer, TreeNode> parent = new HashMap<>();
    public boolean isCousins(TreeNode root, int x, int y) {
        dfs(root, null, 0);
        return depth.get(x).equals(depth.get(y))
                && parent.get(x) != parent.get(y);
    }
    private void dfs(TreeNode node, TreeNode par, int d) {
        if (node == null)
            return;
        depth.put(node.val, d);
        parent.put(node.val, par);
        dfs(node.left, node, d + 1);
        dfs(node.right, node, d + 1);
    }
}