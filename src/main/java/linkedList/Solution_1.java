package scr.main.java.linkedList;

/**
 * 删除链表中的节点
 */
public class Solution_1 {

    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

}
