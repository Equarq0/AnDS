import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String t = in.nextLine();
        int k = 0;
        int l = s.length();
        boolean f = true;
        if (l == t.length()) {
            for (int i = 0; i < l; i++) {
                for (int j = 0; j < l; j++) {
                    if (s.charAt(j) != t.charAt((j + k) % l)) {
                        f = false;
                        break;
                    }
                }
                if (f) {
                    System.out.println(true);
                    return;
                }
                k++;
                f = true;
            }
            System.out.println(false);
        }
        else System.out.println(false);
    }
}