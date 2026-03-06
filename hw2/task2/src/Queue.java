public class Queue {
    private QueueNode head;
    private QueueNode tail;

    public void add(QueueNode node) {
        if (tail == null) {
            head = tail = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
    }

    public QueueNode remove() {
        if (head == null) {
            return null;
        }
        QueueNode temp = head;
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        return temp;
    }

    public int getSize() {
        int size = 0;
        QueueNode current = head;
        while (current != null) {
            size++;
            current = current.getNext();
        }
        return size;
    }
}
