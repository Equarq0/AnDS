import java.util.*;

public class Main {
    private static int time;
    private static int bridgeCount;
    private static int[] tin;
    private static int[] low;
    private static boolean[] visited;

    public static void main(String[] args) {
        // System.out.println(task1(new int[][]{{1, 2}, {3, 4}}));
        // System.out.println(task2(new int[]{1, 4, 5, 100, 23, 17, 18}));
        int[][] matrix1 = {
                {0, 0, 1, 1},
                {0, 0, 0, 0},
                {1, 0, 0, 1},
                {1, 0, 1, 0}
        };

        int[][] matrix2 = {
                {0, 1, 0, 1, 0, 0, 0},
                {1, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 1, 1, 1},
                {1, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 1, 0},
                {0, 1, 1, 0, 1, 0, 0},
                {0, 0, 1, 0, 0, 0, 0}
        };
    }

    public static int task1(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        HashSet<Integer>[][] myDp = new HashSet[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                myDp[i][j] = new HashSet<>();
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    myDp[i][j].add(arr[i][j]);
                }
                else if (i == 0) {
                    myDp[i][j].add(arr[i][j] + myDp[i][j - 1].iterator().next());
                }
                else if (j == 0) {
                    myDp[i][j].add(arr[i][j] + myDp[i - 1][j].iterator().next());
                }
                else {
                    for (int el: myDp[i - 1][j]) {
                        myDp[i][j].add(el + arr[i][j]);
                    }
                    for (int el: myDp[i][j - 1]) {
                        myDp[i][j].add(el + arr[i][j]);
                    }
                }
            }

        }
        return myDp[n - 1][m - 1].size();
    }

    public static int task2(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int res = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(dp[i], res);

        }
        return res;
    }

    public static int task3(int[][] arr) {
        int v = arr.length;
        int e = 0;

        for (int i = 0; i < v; i++) {
            for (int j = i + 1; j < v; j++) {
                if (arr[i][j] == 1) {
                    e++;
                }
            }
        }

        tin = new int[v];
        low = new int[v];
        visited = new boolean[v];
        time = 0;
        bridgeCount = 0;

        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                findBridges(i, -1, arr);
            }
        }

        return e - bridgeCount;
    }

    private static void findBridges(int u, int p, int[][] arr) {
        visited[u] = true;
        tin[u] = low[u] = time++;

        for (int v = 0; v < arr.length; v++) {
            if (arr[u][v] == 0) continue;
            if (v == p) continue;

            if (visited[v]) {
                low[u] = Math.min(low[u], tin[v]);
            } else {
                findBridges(v, u, arr);
                low[u] = Math.min(low[u], low[v]);

                if (low[v] > tin[u]) {
                    bridgeCount++;
                }
            }
        }
    }
    public static long task4(int[] arr, int k) {
        long totalTriplets = 0;
        HashMap<Long, Long> leftCount = new HashMap<>();
        HashMap<Long, Long> rightCount = new HashMap<>();

        for (int x : arr) {
            long val = (long) x;
            rightCount.put(val, rightCount.getOrDefault(val, 0L) + 1);
        }

        for (int x : arr) {
            long current = (long) x;

            rightCount.put(current, rightCount.get(current) - 1);

            if (current % k == 0) {
                long targetLeft = current / k;
                long targetRight = current * k;

                long countLeft = leftCount.getOrDefault(targetLeft, 0L);
                long countRight = rightCount.getOrDefault(targetRight, 0L);

                totalTriplets += countLeft * countRight;
            }

            leftCount.put(current, leftCount.getOrDefault(current, 0L) + 1);
        }

        return totalTriplets;
    }
}