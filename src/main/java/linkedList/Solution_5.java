package scr.main.java.linkedList;

/**
 *请判断一个链表是否为回文链表。
 *
 * 示例 1:
 *
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 *
 * 输入: 1->2->2->1
 * 输出: true
 */
public class Solution_5 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static boolean isPalindrome(ListNode head) {
        ListNode copyHead = null;
        ListNode pre = null;
        ListNode n = head;
        while (n != null) {
            ListNode node = new ListNode(n.val);
            if(null == copyHead) {
                copyHead = node;
                pre = node;
            } else {
                pre.next = node;
                pre = node;
            }
            n = n.next;
        }
        ListNode newHead = reverse(copyHead);
        print(head);
        System.out.println("==============");
        print(newHead);
        while (head != null &&newHead !=null) {
            if(head.val != newHead.val) {
                return false;
            }
            head = head.next;
            newHead = newHead.next;
        }
        if(head == null && newHead == null) {
            return true;
        }
        return false;
    }

    public static ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode head = null;
        ListNode pre = null;
        for (int i = 0; i < 10; i++) {
            if (head == null) {
                head = new ListNode(i);
                pre = head;
            } else {
                ListNode curr = new ListNode(i);
                pre.next = curr;
                pre = curr;
            }
        }
//        print(head);
//        System.out.println("===========");
//        ListNode reverse = reverse(head);
//        print(reverse);

        isPalindrome(head);
    }

    private static void print(ListNode node) {
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }


}
