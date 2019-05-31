package scr.main.java.array;

/**
 * 移动0
 */
public class Solution_8 {

    // 答案1 向后冒泡
    public static void moveZeroes(int[] nums) {
        int l = nums.length;
        for (int i = l - 2; i >= 0; i--) {
            if (nums[i] == 0) {
                int m = i;
                int n = i + 1;
                while (n < l && nums[n] != 0) {
                    int temp = nums[m];
                    nums[m] = nums[n];
                    nums[n] = temp;
                    m++;
                    n++;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 3, 12};
        moveZeroes(arr);
        for (int i : arr) {
            System.out.print(i + ",");
        }
    }

}
