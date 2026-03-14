public class Main {
     public static void main(String[] args) {
        Tree<Integer> myTree = new Tree<>();
        myTree.add(3);
        myTree.add(6);
        myTree.add(5);
        myTree.add(4);
        myTree.add(2);
        myTree.add(1);
        myTree.add(0);

        myTree.remove(5, myTree.getRoot());
        TreeNode<Integer> parent = myTree.getParent(4, myTree.getRoot());
        // System.out.println(parent.getValue());
        // myTree.inOrder(myTree.getRoot());
        // myTree.preOrder(myTree.getRoot());
        // myTree.postOrder(myTree.getRoot());
        // int h = myTree.getHeight(myTree.getRoot());
        // System.out.println(h);

        Tree<Integer> tree1 = new Tree<>();
        tree1.add(10);
        tree1.add(5);
        tree1.add(15);
        tree1.add(12);
        tree1.add(13);

        tree1.remove(15, tree1.getRoot());
        tree1.inOrder(tree1.getRoot());
     }
}