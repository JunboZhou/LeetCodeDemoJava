package _06_二叉搜索树;

import _06_二叉搜索树.printer.BinaryTrees;

import java.util.Comparator;

public class MainTest {
    public static void main(String[] args) {

        test1();
    }

    static void test1() {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);
        bst.remove(7);
        BinaryTrees.println(bst);
//        bst.levelOrder(new BinarySearchTree.Visitor<Integer>() {
//            @Override
//            public boolean visit(Integer element) {
//                System.out.println(element + " ");
//                return element == 4 ? true : false;
//            }
//        });
    }

    static void test2() {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };

        BinarySearchTree<Person> bst1 = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst1.add(new Person(data[i]));
        }

        BinaryTrees.println(bst1);

        BinarySearchTree<Person> bst2 = new BinarySearchTree<>(new Comparator<Person>() {
            public int compare(Person o1, Person o2) {
                return o2.getAge() - o1.getAge();
            }
        });
        for (int i = 0; i < data.length; i++) {
            bst2.add(new Person(data[i]));
        }
        BinaryTrees.println(bst2);
    }
}
