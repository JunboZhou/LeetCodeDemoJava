package _06_二叉搜索树02.tree;

import _06_二叉搜索树02.printer.BinaryTrees;

public class Main {
    static void test1() {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };

        BinSearchTree<Integer> bst = new BinSearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);

        bst.remove(7);

        BinaryTrees.println(bst);
    }

    static void test2() {
        Integer data[] = new Integer[] {
                // 7, 4
                // 7, 4, 9
                // 7, 4, 9, 5
                // 7, 4, 9, 2
                // 7, 4, 9, 2, 8
                7, 4, 9, 2, 1
        };

        BinSearchTree<Integer> bst = new BinSearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);
        System.out.println("----------------------------------");
        System.out.println(bst.isComplete());
    }

    public static void main(String[] args) {
        test2();
    }
}
