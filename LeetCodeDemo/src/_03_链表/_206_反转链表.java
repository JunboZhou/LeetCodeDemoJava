package _03_链表;

/**
 * 206. 反转链表
 * https://leetcode-cn.com/problems/reverse-linked-list/
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 */
public class _206_反转链表 {

    /**
     * 头插法
     */
    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode newHead = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }
        return newHead;
    }
    /**
     * 递归思想
     * 注意:  可以假设 reverseList 函数已经实现功能
     * head = 1
     * 调用 reverseList(head.next) 相当于  reverseList(2)  这个函数假设已经实现反转 null 5 4 3 2
     * 只需要单独处理 head 节点 2.next = 1  , 2.next = null
     * 递归处理 直到head为null
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode newNode = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newNode;
    }

}
