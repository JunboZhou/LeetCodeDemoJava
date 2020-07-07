package _03_链表;

/**
 * 203. 移除链表元素
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 *
 * 删除链表中等于给定值 val 的所有节点。
 * 示例:
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 */
public class _203_去除链表元素 {

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        ListNode newNode = new ListNode(0);
        newNode.next = head;

        ListNode node = newNode;
        while (newNode.next != null) {
            if (newNode.next.val == val) {
                newNode.next = newNode.next.next;
            } else {
                newNode = newNode.next;
            }
        }
        return node.next;
    }

}
