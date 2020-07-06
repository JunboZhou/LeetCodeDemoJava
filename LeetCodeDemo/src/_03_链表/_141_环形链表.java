package _03_链表;

/**
 * 141. 环形链表  https://leetcode-cn.com/problems/linked-list-cycle/
 */
public class _141_环形链表 {

    /**
     * 利用快慢指针思想,如果是环形链表,必然在某个节点相遇
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        // 慢指针
        ListNode slow = head;
        // 快指针
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

}
