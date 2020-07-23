package _06_二叉搜索树.leetcode;

import jdk.nashorn.internal.ir.annotations.Ignore;

import java.time.temporal.Temporal;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 反转二叉树
 * https://leetcode-cn.com/problems/invert-binary-tree/
 */
public class _226_翻转二叉树 {

    /**
     * 层序遍历方法
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }

        }

        return root;
    }

    /**
     * 中序遍历方法
     */
    public TreeNode invertTree3(TreeNode root) {
        if (root == null) return root;

        invertTree(root.right);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        // root.right right  left 和 right的值已经被调换过了
        invertTree(root.right);

        return root;
    }

    /**
     * 后序遍历方法
     */
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) return root;

        invertTree(root.left);
        invertTree(root.right);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        return root;
    }

    /**
     * 前序遍历方法
     */
    public TreeNode invertTree1(TreeNode root) {
        if (root == null) return root;

        invertTree(root.left);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        // root.left 用left原因是  left 和 right的值已经被调换过了
        invertTree(root.left);

        return root;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
