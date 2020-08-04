package _07_AVL树.com.bobz.tree;

import _07_AVL树.com.bobz.printer.BinaryTrees;

public class Main {
    static void test1() {
        Integer data[] = new Integer[] {
                67, 52, 92, 96, 53, 95, 13, 63, 34, 82, 76, 54, 9, 68, 39
        };

        AVLTree<Integer> avl = new AVLTree<>();
        for (int i = 0; i < data.length; i++) {
            avl.add(data[i]);
        }
        BinaryTrees.println(avl);

        avl.postorder(new BinaryTree.Visitor<Integer>() {
            @Override
            boolean visit(Integer element) {
                return false;
            }
        });

    }



    public static void main(String[] args) {
        test1();
    }
}
