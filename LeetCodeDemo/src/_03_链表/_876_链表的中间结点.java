package _03_链表;

import java.util.List;

/***
 * 876. 链表的中间结点
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/
 * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
 *
 * 如果有两个中间结点，则返回第二个中间结点。
 *
 * 示例 1：
 *
 * 输入：[1,2,3,4,5]
 * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
 * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
 * 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
 * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
 * 示例 2：
 *
 * 输入：[1,2,3,4,5,6]
 * 输出：此列表中的结点 4 (序列化形式：[4,5,6])
 * 由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
 *  
 *
 * 提示：
 *
 * 给定链表的结点数介于 1 和 100 之间。
 */
public class _876_链表的中间结点 {

    /**
     * 利用快慢指针思想
     * 用两个指针 slow 与 fast 一起遍历链表。slow 一次走一步，fast 一次走两步。
     * 那么当 fast 到达链表的末尾时，slow 必然位于中间。
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 利用数组解决
     * 对链表进行遍历，同时将遍历到的元素依次放入数组 A 中。如果我们遍历到了 N 个元素，
     * 那么链表以及数组的长度也为 N，对应的中间节点即为 A[N/2]。
     */
    public ListNode middleNode2(ListNode head) {
        ListNode[] Array = new ListNode[100];

        int num = 0;
        while (head != null) {
            Array[num++] = head;
            head = head.next;
        }
        return Array[num >> 1];
    }

    // 使用链表知识
    public ListNode middleNode1(ListNode head) {
        ListNode tempHead = head;
        int num = 1;
        while (tempHead.next != null) {
            tempHead = tempHead.next;
            num ++;
        }

        int index = (num >> 1) + 1;

        ListNode node = head;
        for (int i = 0; i < index - 1 ; i++) {
            node = node.next;
        }
        return node;
    }
}
