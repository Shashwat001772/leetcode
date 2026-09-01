import java.util.*;

class Solution {
    static class State {
        int r, c, mask, energy;
        State(int r, int c, int mask, int energy) {
            this.r = r;
            this.c = c;
            this.mask = mask;
            this.energy = energy;
        }
    }
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int sr = 0;
        int sc = 0;
        int litterCount = 0;
        int[][] id = new int[m][n];
        for (int[] row : id) {
            Arrays.fill(row, -1);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (classroom[i].charAt(j) == 'S') {
                    sr = i;
                    sc = j;
                }
                if (classroom[i].charAt(j) == 'L') {
                    id[i][j] = litterCount++;
                }
            }
        }
        if (litterCount == 0) {
            return 0;
        }
        int target = (1 << litterCount) - 1;
        boolean[][][][] visited =
            new boolean[m][n][1 << litterCount][energy + 1];
        Queue<State> q = new LinkedList<>();
        q.offer(new State(sr, sc, 0, energy));
        visited[sr][sc][0][energy] = true;
        int moves = 0;
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                State cur = q.poll();
                int r = cur.r;
                int c = cur.c;
                int mask = cur.mask;
                int currEnergy = cur.energy;
                if (mask == target) {
                    return moves;
                }
                if (currEnergy == 0) {
                    continue;
                }
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }
                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }
                    int newEnergy = currEnergy - 1;
                    int newMask = mask;
                    if (classroom[nr].charAt(nc) == 'L') {
                        newMask |= (1 << id[nr][nc]);
                    }
                    if (classroom[nr].charAt(nc) == 'R') {
                        newEnergy = energy;
                    }
                    if (visited[nr][nc][newMask][newEnergy]) {
                        continue;
                    }
                    visited[nr][nc][newMask][newEnergy] = true;
                    q.offer(
                        new State(nr, nc, newMask, newEnergy)
                    );
                }
            }
            moves++;
        }
        return -1;
    }
}