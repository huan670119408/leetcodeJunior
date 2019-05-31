package scr.main.java.linkedList;

/**
 * 给定一个链表，判断链表中是否有环。
 */
public class Solution_6 {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public boolean hasCycle(ListNode head) {
        if(head == null){
            return false;
        }
        ListNode curr = head;
        ListNode quick = head;
        while (curr.next != null && quick.next != null && quick.next.next != null) {
            if (curr.next == quick.next.next) {
                return true;
            }
            curr = curr.next;
            quick = quick.next.next;
        }
        return false;
    }

}
