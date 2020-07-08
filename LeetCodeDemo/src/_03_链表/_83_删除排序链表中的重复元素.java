package _03_链表;

/**
 * 83. 删除排序链表中的重复元素
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 *
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 *
 * 示例 1:
 *
 * 输入: 1->1->2
 * 输出: 1->2
 * 示例 2:
 *
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 */
public class _83_删除排序链表中的重复元素 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode tempHead = head;
        while ((tempHead != null) && (tempHead.next != null)) {
            if (tempHead.val == tempHead.next.val) {
                tempHead.next = tempHead.next.next;
            } else {
                tempHead = tempHead.next;
            }
        }
        return head;
    }
}
