package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的层次遍历
 */
public class Solution_4 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private class Pair {

        private TreeNode node;

        private Integer level;

        private Pair(TreeNode node, Integer level) {
            this.node = node;
            this.level = level;
        }

    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<Pair> queue = new LinkedList<Pair>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int currIndex = 0;
        queue.offer(new Pair(root, 1));
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();


            TreeNode node = pair.node;
            Integer level = pair.level;
            if (node.left != null) {
                queue.offer(new Pair(node.left, level + 1));
            }
            if (node.right != null) {
                queue.offer(new Pair(node.right, level + 1));
            }
        }
        return result;
    }

}
