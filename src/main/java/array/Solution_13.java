package main.java.array;

/**
 * 至少是其他数字两倍的最大数
 * <p>
 * 在一个给定的数组nums中，总是存在一个最大元素 。
 * <p>
 * 查找数组中的最大元素是否至少是数组中每个其他数字的两倍。
 * <p>
 * 如果是，则返回最大元素的索引，否则返回-1。
 */
public class Solution_13 {

    public int dominantIndex(int[] nums) {
        if (null == nums) {
            return -1;
        }
        if (nums.length == 1) {
            return 0;
        }
        int first = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[first]) {
                first = i;
            }
        }
        int second = first == 0 ? 1 : 0;
        for (int j = 1; j < nums.length; j++) {
            if (j != first && nums[j] > nums[second]) {
                second = j;
            }
        }
        if (nums[first] >= 2 * nums[second]) {
            return first;
        }
        return -1;
    }

}
