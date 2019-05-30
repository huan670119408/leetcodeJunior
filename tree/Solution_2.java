package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 验证二叉搜索树
 * <p>
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 */
public class Solution_2 {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        }
        List<TreeNode> list = initList(root);
        if(list.size()<=1){
            return true;
        }
        for(int i = 1;i<list.size();i++){
            TreeNode pre = list.get(i - 1);
            TreeNode curr = list.get(i);
            if(pre.val>=curr.val){
                return false;
            }
        }
        return true;
    }

    private List<TreeNode> list = new ArrayList<TreeNode>();

    private List<TreeNode> initList(TreeNode node){
        if(node != null){
            initList(node.left);
            list.add(node);
            initList(node.right);
        }
        return list;
    }

}
