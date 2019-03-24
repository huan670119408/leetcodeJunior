package array;

/**
 * 排序数组中删除重复项
 */
public class Solution_1 {
    public static int removeDuplicates(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        int max = nums[nums.length - 1];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[count-1]) {
                nums[count] = nums[i];
                count++;
            }
            if(nums[count-1] == max) {
                break;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int nums[] = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int count = removeDuplicates(nums);
        System.out.println(count);
        for (int n : nums) {
            System.out.print(n + ";");
        }
    }

}
