public class Main {
    public static void main(String[] args) {
        Queue queue1 = new Queue();
        Queue queue2 = new Queue();
        
        queue1.add(new QueueNode(1));
        queue1.add(new QueueNode(3));
        queue1.add(new QueueNode(5));
        
        queue2.add(new QueueNode(2));
        queue2.add(new QueueNode(4));
        queue2.add(new QueueNode(6));

        Queue result_queue = new Queue();

        QueueNode h1 = null;
        QueueNode h2 = null;

        while (true) {
            if (h1 == null) h1 = queue1.remove();
            if (h2 == null) h2 = queue2.remove();

            if (h1 == null && h2 == null) break;

            if (h1 == null) {
                result_queue.add(h2);
                h2 = null;
            } 
            else if (h2 == null) {
                result_queue.add(h1);
                h1 = null;
            }
             else {
                if (h1.getValue() <= h2.getValue()) {
                    result_queue.add(h1);
                    h1 = null;
                }
                 else {
                    result_queue.add(h2);
                    h2 = null;
                }
            } 
        }

        while (result_queue.getSize() > 0) {
            System.out.println(result_queue.remove().getValue());
        }
    }
}