package _07_AVL树.com.bobz.leetcode;

import apple.laf.JRSUIUtils;

import javax.xml.soap.Node;
import java.util.ArrayList;

/**
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/
 *
 * 剑指 Offer 54. 二叉搜索树的第k大节点
 */
public class _Offer54_二叉搜索树的第k大节点 {

    ArrayList<TreeNode> arrayList = new ArrayList<>();

    public int kthLargest(TreeNode root, int k) {
        if (k == 0 || root == null) return -1;
        inorder(root);
        TreeNode node = arrayList.get(k - 1);
        return node.val;
    }
    public void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.right);
        arrayList.add(root);
        inorder(root.left);
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
