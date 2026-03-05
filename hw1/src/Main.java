import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); 
        int num1 = in.nextInt();
        int num2 = in.nextInt();
        int ost = 1;
        
        while (num2 != 0) {
            ost = num1 % num2;
            num1 = num2;
            num2 = ost;
        }
        System.out.println(num1);
     }
}
