package scr.main.java.array;

/**
 * 旋转数组
 */
public class Solution_3 {
    public static void rotate(int[] nums, int k) {
        if (null == nums || nums.length == 0 || k%nums.length  == 0) {
            return;
        }
        int len = nums.length;
        int start = 0;
        int index = 0;
        int curr = nums[0];
        for (int i = 0; i < len; i++) {
            index = (k + index) % len;
            int temp = nums[index];
            nums[index] = curr;
            curr = temp;
            if(start == index) {
                index = ++start;
                curr = nums[index];
            }
        }
    }

    public static void main(String[] args) {
        int k = 2;
        int[] arr = {1, 2, 3, 4, 5, 6};
        rotate(arr, k);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

}
