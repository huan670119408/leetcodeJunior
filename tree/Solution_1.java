package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 */
public class Solution_1 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private class Pair {
        TreeNode node;
        Integer depth;

        private Pair(TreeNode node, Integer depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<Pair> queue = new LinkedList<Pair>();
        queue.offer(new Pair(root, 1));
        int max = 1;
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            TreeNode node = pair.node;
            Integer depth = pair.depth;
            if (node.left != null) {
                queue.offer(new Pair(node.left, depth + 1));
                max = max>(depth + 1) ? max  : depth + 1;
            }
            if (node.right != null) {
                queue.offer(new Pair(node.right, depth + 1));
                max = max>(depth + 1) ? max  : depth + 1;
            }
        }
        return max;
    }

}
