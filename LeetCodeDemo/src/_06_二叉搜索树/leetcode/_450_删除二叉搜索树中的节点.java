package _06_二叉搜索树.leetcode;

import _06_二叉搜索树.BinarySearchTree;
import sun.awt.geom.AreaOp;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;

/**
 * 删除二叉搜索树中的节点
 * https://leetcode-cn.com/problems/delete-node-in-a-bst/
 *
 * 二叉搜索树的特点是左子树的值都比他小，右子树的值都比他大，删除一个节点之后我们还要保证二叉搜索树的这个特点不变。如果要删除一个结点，我们先要找到这个节点，然后才能删除，但这里要分几种情况。
 *
 * 如果要删除的节点是叶子节点，我们直接删除即可。
 *
 * 如果删除的结点不是叶子节点，并且有一个子节点为空，我们直接返回另一个不为空的子节点即可。
 *
 * 如果删除的结点不是叶子节点，并且左右子树都不为空，我们可以用左子树的最大值替换掉要删除的节点或者用右子树的最小值替换掉要删除的节点都是可以的。
 */
public class _450_删除二叉搜索树中的节点 {

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return root;

        if (key > root.val) { // 查找节点比根节点大,在右子树中查找
            root.right = deleteNode(root.right, key);
        } else if (key < root.val) {  // 在左子树中查找
            root.left = deleteNode(root.left, key);
        } else { // 找到该节点
            //如果左子树为空，我们只需要返回右子树即可
           if (root.left == null) {
               return root.right;
           }
            //如果右子树为空，我们只需要返回左子树即可
           if (root.right == null) {
               return root.left;
           }
            //说明两个子节点都不为空，我们可以找左子树的最大值
            //也可以找右子树的最小值替换

            //这里是用右子树的最小值替换
//            TreeNode s = findMin(root.right);
//            root.val = s.val;
//            root.right = deleteNode(root.right, root.val);

            //这里是用左子树的最大值替换)
            TreeNode max = findMax(root.left);
            root.val = max.val;
            root.left = deleteNode(root.left, root.val);

        }
        return root;

    }

    //  找右子树的最小值
    private TreeNode findMin(TreeNode node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    //  找右子树的最小值
    private TreeNode findMax(TreeNode node) {
        while (node.right != null)
            node = node.right;
        return node;
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
