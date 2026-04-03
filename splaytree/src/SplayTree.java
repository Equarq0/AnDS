/*В рамках данной работы Splay-дерево используется для решения задачи эффективного
 кэширования запросов в системе управления базами данных.
Структура позволяет динамически адаптироваться к нагрузке:
наиболее часто запрашиваемые записи (ключи) перемещаются ближе к корню дерева,
обеспечивая практически мгновенный доступ к «горячим» данным,
в то время как редко используемые данные уходят на периферию.
*/

public class SplayTree<K extends Comparable<K>> {
    private SplayNode<K> root;

    public void insert(K key) {
        if (root == null) {
            root = new SplayNode<>(key);
            return;
        }
        SplayNode<K> curr = root;
        SplayNode<K> lastParent = null;

        while (curr != null) {
            lastParent = curr;
            int cmp = key.compareTo(curr.key);
            if (cmp < 0) curr = curr.left;
            else if (cmp > 0) curr = curr.right;
            else {
                splay(curr);
                return;
            }
        }

        SplayNode<K> newNode = new SplayNode<>(key);
        newNode.parent = lastParent;
        if (key.compareTo(lastParent.key) < 0) lastParent.left = newNode;
        else lastParent.right = newNode;

        splay(newNode);
    }

    public boolean search(K key) {
        SplayNode<K> curr = root;
        while (curr != null) {
            int cmp = key.compareTo(curr.key);
            if (cmp < 0) curr = curr.left;
            else if (cmp > 0) curr = curr.right;
            else {
                splay(curr);
                return true;
            }
        }
        return false;
    }

    public void delete(K key) {
        if (!search(key)) return;

        SplayNode<K> leftSub = root.left;
        SplayNode<K> rightSub = root.right;

        if (leftSub != null) leftSub.parent = null;
        if (rightSub != null) rightSub.parent = null;

        if (leftSub == null) {
            root = rightSub;
        } else {
            SplayNode<K> max = leftSub;
            while (max.right != null) max = max.right;

            // Временно меняем корень для корректного splay в левой части
            SplayNode<K> oldRoot = root;
            root = leftSub;
            splay(max);

            root.right = rightSub;
            if (rightSub != null) rightSub.parent = root;
        }
    }

    private void splay(SplayNode<K> x) {
        while (x.parent != null) {
            SplayNode<K> p = x.parent;
            SplayNode<K> g = p.parent;
            if (g == null) {
                rotate(x);
            } else if ((g.left == p) == (p.left == x)) {
                rotate(p);
                rotate(x);
            } else {
                rotate(x);
                rotate(x);
            }
        }
        root = x;
    }

    private void rotate(SplayNode<K> x) {
        SplayNode<K> p = x.parent;
        SplayNode<K> g = p.parent;
        if (x == p.left) {
            p.left = x.right;
            if (x.right != null) x.right.parent = p;
            x.right = p;
        } else {
            p.right = x.left;
            if (x.left != null) x.left.parent = p;
            x.left = p;
        }
        x.parent = g;
        if (g != null) {
            if (p == g.left) g.left = x;
            else g.right = x;
        }
        p.parent = x;
    }
}