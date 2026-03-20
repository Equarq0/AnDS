public class Task2 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 5, 6, 7, 8};
        int n = arr.length + 1;

        int sm = ((1 + n) * n) / 2;

        int real_sum = 0;
        for (int i = 0; i < n - 1; i++) {
            real_sum += arr[i];
        }
        System.out.println(sm - real_sum);
    }
}
