package main.java.array;

/**
 * 寻找数组的中心索引
 * <p>
 * 我们是这样定义数组中心索引的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
 * <p>
 * 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
 */
public class Solution_12 {

    public int pivotIndex(int[] nums) {
        if (nums == null) {
            return -1;
        }
        int left = 0;
        int right = 0;
        for (int i = 0; i < nums.length; i++) {
            right += nums[i];
        }
        for (int j = 0; j < nums.length; j++) {
            if (j - 1 >= 0) {
                left += nums[j - 1];
            }
            right -= nums[j];
            if (left == right) {
                return j;
            }
        }
        return -1;
    }

}
