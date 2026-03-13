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
        // myTree.inOrder(myTree.getRoot());
        // myTree.preOrder(myTree.getRoot());
        myTree.postOrder(myTree.getRoot());
        int h = myTree.getHeight(myTree.getRoot());
        System.out.println(h);
        System.out.println(myTree.get(5, myTree.getRoot()));
     }
}