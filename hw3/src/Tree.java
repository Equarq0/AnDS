public class Tree<T extends Comparable<T>> {
    private TreeNode<T> root;

    //todo: реализовать в домашке
    // private int height;

    //todo: чтобы быстро создать конструктор или геттеры\сеттеры
    // можно нажать комбинацию клавиш alt + insert (на Mac -- cmd + n)

    public Tree() {
//        this.height = 0;
    }

    public Tree(TreeNode<T> root) {
        this.root = root;
//        this.height = 1;
    }

    public void add(T value) {
        TreeNode<T> node = new TreeNode<>(value);

        if (this.root == null) {
            this.root = node;
//            this.height = 1;
        } else {
            add(this.root, value);
        }
    }

    private void add(TreeNode<T> current, T value) {
        // если текущая нода больше вставляемого значения
        // проверяем, есть ли левый потомок
        // если нет, то добавляем нового потомка
        // если есть, вызываем рекурсивный метод снова
        // если текущая нода меньше вставляемого значения
        // проверяем, есть ли правый потомок и делаем все то же самое
        //
        if (current.getValue().compareTo(value) > 0) {
            if (current.getLeft() == null) {
                current.setLeft(new TreeNode<>(value));
                current.getLeft().setParent(current);
            } else {
                add(current.getLeft(), value);
            }
        } else if (current.getValue().compareTo(value) <= 0) {
            if (current.getRight() == null) {
                current.setRight(new TreeNode<>(value));
                current.getRight().setParent(current);
            } else {
                add(current.getRight(), value);
            }
        }
    }

    public void preOrder(TreeNode<T> node) {
        if (node == null) return;
        System.out.println(node.getValue());
        preOrder(node.getLeft());
        preOrder(node.getRight());
    }

    public void inOrder(TreeNode<T> node) {
        if (node == null) return;
        inOrder(node.getLeft());
        System.out.println(node.getValue());
        inOrder(node.getRight());
    }

    public void postOrder(TreeNode<T> node) {
        if (node == null) return;
        postOrder(node.getLeft());
        postOrder(node.getRight());
        System.out.println(node.getValue());
    }

    public int getHeight(TreeNode<T> node) {
        if (node == null) return 0;
        int l = getHeight(node.getLeft());
        int r = getHeight(node.getRight());
        return (Math.max(l, r)) + 1;
    }

    public T get(T value, TreeNode<T> node) {
        if (node == null) return null;
        if (node.getValue().compareTo(value) == 0) {
            return value;
        }
        if (node.getValue().compareTo(value) > 0) {
            return get(value, node.getLeft());
        }
        if (node.getValue().compareTo(value) < 0) {
                return get(value, node.getRight());
            }
        return null;
    }

    public T remove(T value, TreeNode<T> node) {
        if (node == null) return null;
        if (node.getValue().compareTo(value) == 0) {
            TreeNode<T> parent = node.getParent();
            if (node.getRight() != null) {
                if (node.getRight().getLeft() == null) {
                    TreeNode<T> aim = node;
                    if (parent == null) {
                        this.root = aim.getRight();
                    } else if (aim == parent.getLeft()) {
                        parent.setLeft(aim.getRight());
                    } else {
                        parent.setRight(aim.getRight());
                    }
                    if (aim.getRight() != null) aim.getRight().setParent(parent);
                    if (aim.getLeft() != null) {
                        aim.getRight().setLeft(aim.getLeft());
                        aim.getLeft().setParent(aim.getRight());
                    }
                }
                else {
                    TreeNode<T> replace_node = node.getRight();
                    while (replace_node.getLeft() != null) {
                        replace_node = replace_node.getLeft();
                    }
                    node.setValue(replace_node.getValue());
                    TreeNode<T> p = replace_node.getParent();
                    replace_node = replace_node.getRight();
                    p.setLeft(replace_node);
                    if (replace_node != null) {
                        replace_node.setParent(p);
                    }
                }
                return value;
            }
            else if(node.getLeft() != null) {
                TreeNode<T> aim = node;
                if (parent == null) {
                    this.root = aim.getLeft();
                } else if (aim == parent.getLeft()) {
                    parent.setLeft(aim.getLeft());
                } else {
                    parent.setRight(aim.getLeft());
                }
                if (aim.getLeft() != null) aim.getLeft().setParent(parent);
                return value;
            }
            else {
                TreeNode<T> aim = node;
                if (parent == null) {
                    this.root = null;
                } else if (aim == parent.getLeft()) {
                    parent.setLeft(null);
                } else {
                    parent.setRight(null);
                }
                return value;
            }
        }

        if (node.getValue().compareTo(value) > 0) {
            return remove(value, node.getLeft());
        }

        if (node.getValue().compareTo(value) < 0) {
            return remove(value, node.getRight());
        }
        return null;
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public TreeNode<T> getParent(T value, TreeNode<T> node) {
        if (node == null) return null;
        if (node.getValue().compareTo(value) == 0) {
            return node.getParent();
        }
        if (node.getValue().compareTo(value) > 0) {
            return getParent(value, node.getLeft());
        }
        if (node.getValue().compareTo(value) < 0) {
            return getParent(value, node.getRight());
        }
        return null;
    }
}