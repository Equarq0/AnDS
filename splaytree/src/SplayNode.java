public class SplayNode<K extends Comparable<K>> {
    K key;
    SplayNode<K> left, right, parent;

    public SplayNode(K key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Node{key=" + key + "}";
    }
}