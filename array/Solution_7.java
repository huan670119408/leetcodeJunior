package array;

/**
 * 加一
 */
public class Solution_7 {

    public static int[] plusOne(int[] digits) {
        int l = digits.length;
        boolean flag = true;
        for (int i : digits) {
            if (i != 9) {
                flag = false;
                break;
            }
        }
        if (flag) {
            int[] result = new int[l + 1];
            result[0] = 1;
            for (int i = 1; i < l + 1; i++) {
                result[i] = 0;
            }
            return result;
        }
        int[] result = new int[l];
        boolean add = true;// 是否进位
        for (int i = l - 1; i >= 0; i--) {
            if (add) {
                if (digits[i] == 9) {
                    result[i] = 0;
                } else {
                    result[i] = digits[i] + 1;
                    add = false;
                }
            } else {
                result[i] = digits[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 2, 2, 9};
        int[] result = plusOne(arr);
        for (int i : result) {
            System.out.print(i + ",");
        }
    }

}
