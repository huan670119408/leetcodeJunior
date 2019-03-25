package array;

/**
 * 两数之和
 * [2, 7, 11, 15], target = 9
 */
public class Solution_9 {

    public static int[] twoSum(int[] nums, int target) {
        if (null == nums || nums.length < 2) {
            return null;
        }
        int[] result = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum(arr, target);
        if (result != null) {
            for (int i : result) {
                System.out.print(i + ",");
            }
        }
    }

}
