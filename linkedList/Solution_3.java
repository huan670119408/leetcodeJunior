package linkedList;

/**
 * 反转链表
 */
public class Solution_3 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode curr = head;
        ListNode currNext = head.next;
        while (currNext != null) {
            if (curr == head) {
                curr.next = null;
            }
            ListNode next = currNext.next;
            currNext.next = curr;
            curr = currNext;
            currNext = next;
        }
        return curr;
    }

}
