import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
    }
    public static void task1() {
        int[] arr = {3, 7, 1, 2, 6, 4, 9, 5, 8, 0};
        int target = 5;
        HashSet<Integer> visited = new HashSet<>();
        for (int el: arr) {
            int diff = target - el;
            if (visited.contains(diff)) {
                System.out.println(diff + " " + el);
                break;
            }
            visited.add(el);
        }
    }
    public static void task2() {
        String[] arr = {"bsdfd", "asdfsd", "dtertre", "cbnbvvbn"};
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(Arrays.toString(arr));
    }

    public static void task3() {
        int[] arr = {1, 2, 3, 4, 6, 7, 10};
        int target = 7;
        int left = 0;
        int right = arr.length - 1;
        while (right > left) {
            if (arr[left] + arr[right] > target) {
                right -= 1;
            }
            else if (arr[left] + arr[right] < target) {
                left += 1;
            }
            else {
                System.out.println(arr[left] + " " + arr[right]);
                break;
            }
        }
    }
    public static void task4() {
        Integer[] arr = {3, 30, 34, 5, 9};
        Arrays.sort(arr, (a, b) -> {
            int copy_a = a;
            int copy_b = b;
            int lena = 0;
            int lenb = 0;
            while (a > 0) {
                lena += 1;
                a /= 10;
            }
            while (b > 0) {
                lenb += 1;
                b /= 10;
            }
            if (copy_a == 0) lena = 1;
            if (copy_b == 0) lenb = 1;
            double ab = copy_a * Math.pow(10, lenb) + copy_b;
            double ba = copy_b * Math.pow(10, lena) + copy_a;
            return Double.compare(ba, ab);
        });
        if (arr[0] == 0) {
            System.out.println(0);
        }
        else {
            for (int el: arr) {
                System.out.print(el);
            };
        }
    }
}