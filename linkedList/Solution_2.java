package linkedList;

/**
 * 删除链表的倒数第N个节点
 */
public class Solution_2 {

    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || n< 1) {
            return head;
        }
        if(n==1 && head.next == null) {
            return null;
        }
        ListNode p = head;
        ListNode q = head;
        while (n>0 && p.next != null) {
            p = p.next;
            n =n-1;
        }
        if(n!=0) {
            head = head.next;
            return head;
        }
        while (p.next != null) {
            p = p.next;
            q= q.next;
        }
        q.next = q.next.next;
        return head;
    }



}
