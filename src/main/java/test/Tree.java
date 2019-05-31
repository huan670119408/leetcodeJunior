package main.java.test;

/**
 * Created by LiBingyi on 2019/5/25 10:29
 */
public class Tree {

    private Node root;

    private class Node {

        private int val;

        private Node left;

        private Node right;

        Node(int i) {
            val = i;
        }

    }

    public void insert(int i) {
        Node n = new Node(i);
        Node curr = root;
        if (root == null) {
            root = n;
        } else {
            if (i > curr.val) {
                Node p = curr;
                curr = curr.right;
                if (curr == null) {
                    p.right = n;
                    return;
                }
            } else {
                Node p = curr;
                curr = curr.left;
                if (curr == null) {
                    p.left = n;
                    return;
                }
            }
        }
    }

    public Node search(int k) {
        if (null == root) {
            return null;
        }
        Node curr = root;
        while (curr != null) {
            if (curr.val == k) {
                return curr;
            } else if (curr.val > k) {
                curr = curr.left;
                if (curr == null) {
                    return null;
                }
            } else {
                curr = curr.right;
                if (curr == null) {
                    return null;
                }
            }
        }
        return null;
    }

    public void pre(Node n){
        if(null == n){
          return;
        }
        System.out.println(n.val);
        pre(n.left);
        pre(n.left);

    }

}
