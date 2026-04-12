import com.sun.source.tree.Tree;

import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
    }

    public static void task1() {
        int[] arr = {1, 3, 4, 5, 7, 9, 11};
        int target = 4;
        int left = 0;
        int right = arr.length - 1;
        while (right >= left) {
            int mid = (right - (right - left) / 2);
            if (target == arr[mid]) {
                System.out.println(mid);
                break;
            }
            else if (target > arr[mid]) {
                left = mid + 1;
            }
            else if (target < arr[mid]) {
                right = mid - 1;
            }
        }
    }

    public static void task2() {
        int first = 50000;
        long second = 2500000000L;

        if ((long) first * first == second) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }

    public static void task3() {
        String st = "I love ITIS";
        String[] words = st.split(" ");
        for (int i = words.length - 1; i >= 0; i--) {
            System.out.print(words[i] + " ");
        }
        System.out.println();
    }

    static class Tree<T> {
        T value;
        Tree<T> left;
        Tree<T> right;

        Tree(T value) { this.value = value; }
    }

    public static void task4() {
        Tree<Integer> root = new Tree<>(10);
        root.left = new Tree<>(3);
        root.right = new Tree<>(5);
        calculate(root);
    }

        public static int calculate(Tree<Integer> node) {
            if (node == null) return 0;
            int ls = calculate(node.left);
            int rs = calculate(node.right);
            int ss = ls + rs;
            System.out.println("Node " + node.value + " sum: " + ss);
            return node.value + ss;
        }
    public static void task5() {
        int[] arr = {-1, 2, 1, -4};
        int target = 1;
        Arrays.sort(arr);
        int closestSum = arr[0] + arr[1] + arr[2];

        for (int i = 0; i < arr.length - 2; i++) {
            int left = i + 1;
            int right = arr.length - 1;
            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];
                if (Math.abs(target - sum) < Math.abs(target - closestSum)) {
                    closestSum = sum;
                }
                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    System.out.println(sum);
                    return;
                }
            }
        }
        System.out.println(closestSum);
    }
    public static void task6() {
        int[] a = {1, 5, 10, 20}, b = {3, 5, 10, 15}, c = {5, 8, 10, 12};
        HashSet<Integer> set = new HashSet<>();
        for (int x : a) set.add(x);
        HashSet<Integer> intersect = new HashSet<>();
        for (int x : b) if (set.contains(x)) intersect.add(x);
        for (int x : c) if (intersect.contains(x)) System.out.println(x);
    }
}
