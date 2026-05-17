public class Main {
    public static void main(String[] args) {
        System.out.println(task1(70, 70));
    }

    public static int task1(int n, int m) {
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0) arr[i][j] = 1;
                else arr[i][j] = arr[i - 1][j] + arr[i][j - 1];
            }
        }
        return arr[n - 1][m - 1];
    }
}