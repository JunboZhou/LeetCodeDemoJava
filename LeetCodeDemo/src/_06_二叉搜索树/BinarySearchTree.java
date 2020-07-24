package _06_二叉搜索树;

import _06_二叉搜索树.printer.BinaryTreeInfo;
import com.sun.xml.internal.bind.v2.model.core.ID;
import sun.rmi.server.InactiveGroupException;

import javax.sound.midi.Soundbank;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<E> implements BinaryTreeInfo {
    private int size;
    private Node<E> root;
    private Comparator<E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    // 添加节点
    public void add(E element) {
        // 添加的是第一个节点
        if (root == null) {
            root = new Node<>(element, null);
            size++ ;
            return;
        }
        // 添加的是不会第一个节点
        // 找到父节点
        Node<E> parent = root;
        Node<E> node = root;
        int cmp = 0;
        do {
            cmp = compare(element, node.element);
            parent = node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {  // 元素相等
                node.element = element;
            }
        } while (node != null);

        // 查找插入父节点的那个位置
        Node<E> newNode = new Node<>(element, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++ ;
    }

    public void remove(E element) {
        remove(node(element));
    }

    public boolean contains(E element) {
        return node(element) != null;
    }

    private void remove(Node<E> node) {
        if (node == null) return;
        size-- ;

        // 度为 2 的节点
        if (node.hasTwoChildren()) {
            // 找到前驱节点或者后继节点
            Node<E> s = successor(node);
            // 用后继节点的值覆盖度为2的节点
            node.element = s.element;
            // 删除后继节点
            node = s;
        }

        // 删除node节点（node的度必然是1或者0）
        Node<E> replacement = node.left != null ? node.left : node.right;
        if (replacement != null) { // node为度为1的节点
            replacement.parent = node.parent;
            if (node.parent == null) { //  node是度为1的节点并且是根节点
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }
        } else if (node.parent == null) { // node是度为1的节点并且是根节点
            root = null;
        } else {  // node是叶子节点，但不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        }

    }

    /**
     * 根据元素获得节点
     */
    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp == 0) {
                return node;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }

    /**
     * 前驱节点
     */
    private Node<E> predecessor(Node<E> node) {
        if (node == null) return node;

        // 前驱节点在左子树当中  终止条件right == null
        Node<E> p = node.left;
        if (p != null) {
            while (p != null) {
                p = p.right;
            }
            return p;
        }

        // 从父节点、祖父节点寻找前驱节点
        if (node.parent != null && node == node.parent.left) {
            node = node.left;
        }

        return node.parent;
    }

    /**
     * 后驱节点
     */
    private Node<E> successor(Node<E> node) {
        if (node == null) return node;
        // 后驱节点在右子树当中  终止条件left == null
        Node<E> p = node.right;
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        // 从父节点、祖父节点寻找前驱节点
        if (node.parent != null && node == node.parent.right) {
            node = node.right;
        }
        return node.parent;
    }

    /**
     * 几种遍历方法 可以根据条件停止
     */
    /**
     * 前序遍历: 根节点 左子树 右子树
     */
    public void preorder(Visitor<E> visitor) {
        if (visitor == null) return;
        preorder(root, visitor);
    }

    private void preorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;

        visitor.stop = visitor.visit(node.element);
        preorder(node.left, visitor);
        preorder(node.right, visitor);
    }
    /**
     * 中序遍历: 左子树 根节点 右子树
     */
    public void inorder(Visitor<E> visitor) {
        if (visitor == null) return;
        inorder(root, visitor);
    }
    private void inorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;

        inorder(node.left, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        inorder(node.right, visitor);
    }
    /**
     * 后续遍历: 左子树 右子树 根节点
     */
    public void postorder(Visitor<E> visitor) {
        if (visitor == null) return;
        postorder(root, visitor);
    }

    private void postorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;
        postorder(node.left, visitor);
        postorder(node.right, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
    }
    /**
     * 层续遍历: 从上往下 从左往右 一次访问每一个节点
     */
    public void levelOrder(Visitor<E> visitor) {
        if (root == null || visitor.stop) return;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (visitor.visit(node.element)) return;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    /**
     * 判断一棵树是否为完全二叉树
     */
    public boolean isComplete() {
        if (root == null) return false;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        boolean isLeaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (isLeaf && !node.isLeaf()) return false;
            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) {
                return false;
            }
            if (node.right != null) {
                queue.offer(node.right);
            } else {  // 后面遍历的节点必须是叶子节点
                isLeaf = true;
            }
        }
        return true;
    }

    /**
     * 计算二叉树的高度 (队列方法)
     */
    public int height() {
        if (root == null) return 0;
        int height = 0;
        // 每个层级中的元素个数
        int levelSize = 1;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            levelSize-- ;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }

            // 每个层级遍历完
            if (levelSize == 0) {
                levelSize = queue.size();
                height++ ;
            }
        }
        return height;
    }

    /**
     * 计算二叉树的高度 (递归方法)
     */
    public int height1() {
        return height(root);
    }
    private int height(Node<E> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }
    /*
    // 前序遍历
    public void preorderTraversal() {
        preorderTraversal(root);
    }

    private void preorderTraversal(Node<E> node) {
        if (node == null) return;
        System.out.println(node.element);
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    // 中序遍历
    public void inorderTraversal() {
        inorderTraversal(root);
    }

    private void inorderTraversal(Node<E> node) {
        if (node == null) return;
        inorderTraversal(node.left);
        System.out.println(node.element);
        inorderTraversal(node.right);
    }

    // 后续遍历
    public void postorderTraversal() {
        postorderTraversal(root);
    }

    private void postorderTraversal(Node<E> node) {
        if (node == null) return;
        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.println(node.element);
    }

    // 层序遍历
    public void levelOrderTraversal() {
        if (root == null) return;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            System.out.println(node.element);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }
   */
    /**
     * @return 返回值等于0，代表e1和e2相等；返回值大于0，代表e1大于e2；返回值小于于0，代表e1小于e2
     */
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);
    }

    /**
     * 遍历停止条件
     * @return
     */
    public static abstract class Visitor<E> {
        // 是否需要停止属性
        boolean stop;

       public abstract boolean visit(E element);
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).right;
    }

    @Override
    public Object string(Object node) {
        Node<E> myNode = (Node<E>) node;
        String parentString = "null";
        if (myNode.parent != null) {
            parentString = myNode.parent.element.toString();
        }
        return myNode.element;
    }

    private static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }
        // 叶子节点
        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }
    }
}
