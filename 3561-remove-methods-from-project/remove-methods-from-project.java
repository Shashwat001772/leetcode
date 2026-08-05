import java.util.*;
class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<Integer>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int[] e : invocations) g[e[0]].add(e[1]);
        boolean[] bad = new boolean[n];
        dfs(k, g, bad);
        for (int[] e : invocations) {
            if (!bad[e[0]] && bad[e[1]]) {
                List<Integer> ans = new ArrayList<>();
                for (int i = 0; i < n; i++) ans.add(i);
                return ans;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!bad[i]) ans.add(i);
        }
        return ans;
    }
    void dfs(int u, List<Integer>[] g, boolean[] bad) {
        bad[u] = true;
        for (int v : g[u]) {
            if (!bad[v]) dfs(v, g, bad);
        }
    }
}