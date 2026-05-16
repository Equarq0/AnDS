import java.util.Arrays;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println(task1(21));
        System.out.println(task2(7));
        task3("abcdefghn");
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(task4(arr));
        System.out.println(task5(new int[]{3, 4, 6, 9, 13, 14}));
    }

    public static boolean task1(int num) {
        int first = 1;
        int second = 1;
        int res = 0;
        if (num == 1) return true;
        while (res < num + 1) {
            if (res == num) return true;
            res = first + second;
            first = second;
            second = res;
        }
        return false;
    }

    public static int task2(int n) {
        int[] arr = new int[n];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < n; i++) {
            arr[i] = (arr[i - 1] + arr[i - 2]) % 10;
        }
        return arr[n - 1];
    }

    public static void task3(String st) {
        int l = st.length();
        if (l < 2) return;
        int a = 1, b = 1;
        System.out.print(st.charAt(a));
        System.out.print(st.charAt(b));
        while (true) {
            int next = a + b;
            if (next >= l) break;
            System.out.print(st.charAt(next));
            a = b;
            b = next;
        }
        System.out.println();
    }

    public static int task4(int[][] arr) {
        int res = 0;
        int x = 0;
        int y = 0;
        int n = arr.length;
        int m = arr[0].length;
        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) a[i][j] = arr[i][j];
                else if (i == 0) {
                    a[i][j] = arr[i][j] + a[i][j - 1];
                } else if (j == 0) {
                    a[i][j] = arr[i][j] + a[i - 1][j];
                } else {
                    a[i][j] = arr[i][j] + Math.min(a[i - 1][j], a[i][j - 1]);
                }
            }
        }
        return a[n - 1][m - 1];
    }

    public static int task5(int[] arr) {
        Arrays.sort(arr);
        int[] dp = new int[arr.length];
        dp[1] = arr[1] - arr[0];
        dp[2] = arr[2] - arr[0];
        for (int i = 3; i < arr.length; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + arr[i] - arr[i - 1];
        }
        return dp[arr.length - 1];
    }
}