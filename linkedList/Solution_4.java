package linkedList;

/**
 * 合并两个有序链表
 */
public class Solution_4 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode curr = head;
        while (l1!=null&&l2!=null) {
            if(l1.val<=l2.val) {
                curr.next = l1;
                curr = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                curr = l2;
                l2 = l2.next;
            }
        }
        if(null == l1) {
            curr.next = l2;
        } else {
            curr.next = l2;
        }
        return  head.next;
    }

}
