public class Main {
    public static void main(String[] args) {
        String input = "3 4 5 + 2 * +"; 
        
        Stack stack = new Stack();

        String[] tokens = input.split(" ");

        for (String token : tokens) {
            if (isDigit(token)) {

                int value = Integer.parseInt(token);
                stack.add(new StackNode(value));
            } else {

                StackNode node2 = stack.remove();
                StackNode node1 = stack.remove();

                if (node1 == null || node2 == null) {
                    System.out.println("Ошибка: не хватает операндов!");
                    return;
                }

                int b = node2.getValue();
                int a = node1.getValue();
                int result = 0;

                switch (token) {
                    case "+": result = a + b; break;
                    case "-": result = a - b; break;
                    case "*": result = a * b; break;
                    case "/": result = a / b; break;
                }

                stack.add(new StackNode(result));
            }
        }

        StackNode finalResult = stack.remove();
        if (finalResult != null) {
            System.out.println("Результат вычисления: " + finalResult.getValue());
        }
    }

    private static boolean isDigit(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}