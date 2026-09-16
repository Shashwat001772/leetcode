class Solution {
    public int numberOfSets(int n, int k) {
        final int MOD = 1_000_000_007;       
        long[] prev = new long[n]; 
        Arrays.fill(prev, 1L);         
        long[] curr = new long[n];      
        for (int j = 1; j <= k; j++) {
            long[] prefix = new long[n];
            prefix[0] = prev[0];
            for (int i = 1; i < n; i++) {
                prefix[i] = (prefix[i - 1] + prev[i]) % MOD;
            }          
            curr = new long[n];
            curr[0] = 0;
            for (int i = 1; i < n; i++) {
                curr[i] = (curr[i - 1] + prefix[i - 1]) % MOD;
            }            
            prev = curr;
        }
        return (int) (prev[n - 1] % MOD);
    }
}