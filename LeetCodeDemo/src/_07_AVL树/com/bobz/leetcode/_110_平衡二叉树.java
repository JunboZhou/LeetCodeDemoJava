package _07_AVL树.com.bobz.leetcode;

/**
 * https://leetcode-cn.com/problems/balanced-binary-tree/
 */
public class _110_平衡二叉树 {

    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return (Math.abs(height(root.left) - height(root.right)) <= 1) && isBalanced(root.left) && isBalanced(root.right);
    }

    public int height(TreeNode node) {
        if (node == null) return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
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
